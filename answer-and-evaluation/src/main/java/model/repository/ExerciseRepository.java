package model.repository;

import model.domain.ID;
import model.domain.content.Exercise;
import model.domain.content.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class ExerciseRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ExerciseRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Long> findKey(ID<Exercise> id) {
        return jdbcTemplate.queryForStream(
                "SELECT key FROM exercise_content WHERE id = :id",
                Map.of("id", id.id()),
                (r, i) -> r.getLong(1)
        ).findAny();

    }

    public Optional<Exercise> findById(ID<Exercise> id) {
        var sql = """
                  SELECT e.id as id, e.question_type as questionType, q.id as question
                  FROM exercise_content e
                  LEFT JOIN (
                      SELECT key, id FROM chooseaword_content
                      UNION ALL
                      SELECT key, id FROM fillaword_content
                      ) as q
                  ON q.key
                        IN (SELECT question FROM exercise_content_questions ecq WHERE ecq.exercise = e.key)
                  WHERE e.id = :id
                  """;

        var resultList = jdbcTemplate.queryForList(sql, Map.of("id", id.id()));
        return fromList(resultList).stream().findFirst();
    }

    private List<Exercise> fromList(List<Map<String, Object>> list) {
        var result = new HashMap<String, Exercise>();

        for (Map<String, Object> row : list) {
            var exercise = result.computeIfAbsent(((String) row.get("id")), (k) -> getRawExercise(row));

            Optional.ofNullable((String) row.get("question"))
                    .map(i -> new ID<>(exercise.questionType().questionClass(), i))
                    .ifPresent(exercise.questions()::add);
        }

        return result.values().stream().toList();
    }

    private Exercise getRawExercise(Map<String, Object> row) {
        return new Exercise(
                new ID<>(Exercise.class, (String) row.get("id")),
                QuestionType.fromString((String) row.get("questionType")),
                new ArrayList<>()
        );
    }

    ;
}
