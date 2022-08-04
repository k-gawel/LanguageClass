package model.repository;

import model.domain.*;
import model.domain.answer.ExerciseAnswer;
import model.domain.answer.QuestionAnswer;
import model.domain.content.Exercise;
import model.domain.user.Student;
import model.repository.criteria.ExerciseAnswerCriteria;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ExerciseAnswerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ExerciseAnswerRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<ExerciseAnswer> findById(ID<ExerciseAnswer> id) {
        var criteria = ExerciseAnswerCriteria.builder().ids(List.of(id.id())).build();
        return findByCriteria(criteria).stream().findFirst();
    }

    public List<ExerciseAnswer> findByCriteria(ExerciseAnswerCriteria criteria) {
        var query = """
                    SELECT a.id as id, au.id as author, a.created_at as createdAt,
                           ec.id as exercise, qa.id as questionAnswer
                    FROM exercise_answer a
                    JOIN app_user au on a.student = au.key
                    JOIN exercise_content ec on a.exercise = ec.key
                    LEFT JOIN question_answer qa on qa.key IN
                            (SELECT question_answer FROM exercise_answer_answers WHERE exercise_answer = a.key)
                   """ + getConditions(criteria)
                        + (criteria.limit() != null ? " LIMIT :limit " : "")
                        + (criteria.offset()!= null ? " OFFSET :offest " : "");

        var resultList =  jdbcTemplate.queryForList(query, criteria);
        return fromResultList(resultList);
    }

    private String getConditions(ExerciseAnswerCriteria criteria) {
        return " WHERE " + Stream.of(
                criteria.ids() != null ? "a.id IN (:ids)" : "",
                criteria.exercises() != null ? "e.id IN (:exercises)" : "",
                criteria.authors() != null ? "au.id IN (:authors)" : "",
                criteria.startDate() != null ? "createdAt > :startDate" : "",
                criteria.endDate() != null ? "createdAt < :endDate" : ""
        ).filter(s -> !s.isBlank()).collect(Collectors.joining(" AND "));
    }

    private List<ExerciseAnswer> fromResultList(List<Map<String, Object>> resultList) {
        var result = new HashMap<String, ExerciseAnswer>();

        for (Map<String, Object> row : resultList) {
            var id = (String) row.get("id");
            result.putIfAbsent(id, getRaw(row));

            Optional.ofNullable((String) row.get("questionAnswer"))
                    .map(i -> new ID<>(QuestionAnswer.class, i))
                    .ifPresent(i -> result.get(id).answers().add(i));
        }

        return result.values().stream().toList();
    }

    private ExerciseAnswer getRaw(Map<String, Object> rs) {
        return new ExerciseAnswer(
                new ID<>(ExerciseAnswer.class, (String) rs.get("id")),
                new ID<>(Exercise.class, (String) rs.get("exercise")),
                new ID<>(Student.class, (String) rs.get("author")),
                new ArrayList<>(),
                (Timestamp) rs.get("createdAt")
        );
    }


}
