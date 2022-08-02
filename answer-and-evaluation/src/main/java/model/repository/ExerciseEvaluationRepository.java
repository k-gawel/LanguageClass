package model.repository;

import model.domain.ID;
import model.domain.answer.ExerciseAnswer;
import model.domain.evaluation.ExerciseEvaluation;
import model.domain.evaluation.QuestionEvaluation;
import model.domain.user.Teacher;
import model.repository.criteria.ExerciseEvaluationCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ExerciseEvaluationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ExerciseEvaluationRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<ExerciseEvaluation> findById(ID<ExerciseEvaluation> id) {
        return find(ExerciseEvaluationCriteria.builder().ids(List.of(id.id())).build()).stream().findAny();
    }

    public List<ExerciseEvaluation> find(ExerciseEvaluationCriteria criteria) {
        var query = """
            SELECT e.id as id, e.rating as score, e.comment as comment,
                   u.id as author, a.id as answer,
                   e.created_at as createdAt, qe.id as questionId,
                   au.id as answerStudent
            FROM exercise_evaluation AS e
            JOIN app_user u on u.key = e.teacher
            JOIN exercise_answer a on e.answer = a.key
            JOIN app_user au ON au.key = a.student
            LEFT JOIN question_evaluation qe on qe.key IN
                (SELECT question_evaluation FROM exercise_evaluation_question_evaluation WHERE exercise_evaluation = e.key)
            """ + getContidtions(criteria) +
            (criteria.limit() != null ? " LIMIT :limit " : "") +
            (criteria.offset() != null ? " OFFSET :offset" : "");

        var resultList = jdbcTemplate.queryForList(query, criteria);
        return fromRaw(resultList);
    }

    private String getContidtions(ExerciseEvaluationCriteria criteria) {
        var conditions = new ArrayList<String>();
        Optional.ofNullable(criteria.ids()).ifPresent(i -> conditions.add("e.id IN (:ids)"));
        Optional.ofNullable(criteria.authors()).ifPresent(i -> conditions.add("u.id IN (:authors)"));
        Optional.ofNullable(criteria.answers()).ifPresent(i -> conditions.add("a.id IN (:answers)"));
        Optional.ofNullable(criteria.students()).ifPresent(i -> conditions.add("au.id IN (:students)"));
        Optional.ofNullable(criteria.startDate()).ifPresent(i -> conditions.add("createdAt > :startDate"));
        Optional.ofNullable(criteria.endDate()).ifPresent(i -> conditions.add("createdAt < :endDate"));
        return " WHERE " + String.join(" AND ", conditions);
    }

    private List<ExerciseEvaluation> fromRaw(List<Map<String, Object>> rowList) {
        var rawEvaluations = new HashMap<String, ExerciseEvaluation>();

        for (Map<String, Object> row : rowList) {
            String id = (String) row.get("id");
            rawEvaluations.putIfAbsent(id, getRawEvaluation(row, id));

            Optional.ofNullable((String) row.get("questionId"))
                    .map(i -> new ID<>(QuestionEvaluation.class, i))
                    .ifPresent(i -> rawEvaluations.get(id).questionEvaluations().add(i));
        }

        return rawEvaluations.values().stream()
                .sorted(Comparator.comparing(ExerciseEvaluation::createdAt).reversed())
                .toList();
    }

    private ExerciseEvaluation getRawEvaluation(Map<String, Object> row, String id) {
        return new ExerciseEvaluation(
                new ID<>(ExerciseEvaluation.class, id),
                new ID<>(Teacher.class, (String) row.get("author")),
                new ID<>(ExerciseAnswer.class, (String) row.get("answer")),
                new ArrayList<>(),
                (String) row.get("comment"),
                (int) row.get("score"),
                (Timestamp) row.get("createdAt")
        );
    }

}
