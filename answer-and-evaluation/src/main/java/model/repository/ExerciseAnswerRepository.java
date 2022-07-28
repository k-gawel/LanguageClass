package model.repository;

import model.domain.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class ExerciseAnswerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ExerciseAnswerRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<ExerciseAnswer> findById(ID<ExerciseAnswer> id) {
        return findByIds(List.of(id)).stream().findFirst();
    }

    public List<ExerciseAnswer> findByIds(Collection<ID<ExerciseAnswer>> ids) {
        var query = "SELECT a.id as id, au.id as studentId, ec.id AS exerciseId, eaa.question_answer as questionAnswerId " +
                    "FROM exercise_answer a  " +
                    "JOIN app_user au ON a.student = au.key " +
                    "JOIN exercise_content ec on a.exercise = ec.key " +
                    "JOIN exercise_answer_answers eaa on a.key = eaa.exercise_answer " +
                    "WHERE a.id IN :ids";
        var param = Map.of("ids", ids.stream().map(ID::id).toList());

        return jdbcTemplate.queryForObject(query, param, mapper);
    }

    private final RowMapper<List<ExerciseAnswer>> mapper = (r, i) -> {
        var result = new LinkedList<ExerciseAnswer>();

        while(r.next()) {
            if (!result.isEmpty() && r.getString("id").equals(result.getLast().id().id())) {
                var answerId = new ID<>(QuestionAnswer.class, r.getString("questionAnswerId"));
                result.getLast().answers().add(answerId);
            } else {
                var initial = createFromResult(r);
                result.push(initial);
            }
        }

        return result;
    };

    private static ExerciseAnswer createFromResult(ResultSet resultSet) throws SQLException {
        var initialAnswers = new ArrayList<ID<QuestionAnswer>>();
        initialAnswers.add(new ID<>(QuestionAnswer.class, resultSet.getString("questionAnswerId")));
        return new ExerciseAnswer(
                new ID<>(ExerciseAnswer.class, resultSet.getString("id")),
                new ID<>(Exercise.class, resultSet.getString("exerciseId")),
                new ID<>(Student.class, resultSet.getString("studentId")),
                initialAnswers
        );
    }

}
