package model.repository;

import model.domain.*;
import model.domain.answer.QuestionAnswer;
import model.domain.content.ChooseAWordQuestion;
import model.domain.content.Question;
import model.domain.user.Student;
import model.repository.criteria.QuestionAnswerCriteria;
import model.repository.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;

@Repository
public class QuestionAnswerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String tableName = "question_answer";

    @Autowired
    public QuestionAnswerRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, QuestionRepository questionRepository) {
        jdbcTemplate = namedParameterJdbcTemplate;
    }


    public Optional<Long> findKey(ID<QuestionAnswer> answer) {
        var sql = "SELECT key FROM " + tableName + " WHERE id = :id";
        var params = Map.of("id", answer.id());
        return jdbcTemplate.queryForStream(sql, params, (r, i) -> r.getLong("key")).findFirst();
    }

    public List<QuestionAnswer> findByCriteria(QuestionAnswerCriteria criteria) {
        var queryBuilder = new StringBuilder("""
                    SELECT a.id as id, null as createdAt, a.answers as answers,
                           s.id as student, q.id as question, q.type as type
                    FROM question_answer AS a
                    INNER JOIN(
                        SELECT key, id, 'choose-a-word' as type FROM chooseaword_content
                        UNION ALL
                        SELECT key, id, 'fill-a-word' as type FROM fillaword_content
                    ) AS q ON a.question = q.key AND (:questions IS NULL OR q.id IN :questions)
                    INNER JOIN
                    app_user s ON a.student = s.key AND (:students IS NULL OR s.id IN :students)
                    WHERE (:ids IS NULL OR a.id IN :ids)
                    AND (:startDate IS NULL OR a.created_at >= :startDate)
                    AND (:endDate IS NULL OR a.created_at <= :endDate)\n
                    """);

        Map<String, Object> params = new HashMap<>();
        params.put("ids", criteria.ids());
        params.put("students", criteria.students());
        params.put("questions", criteria.questions());
        params.put("startDate", criteria.startDate());
        params.put("endDate", criteria.endDate());

        Optional.ofNullable(criteria.offset()).ifPresent(o -> {
            params.put("offset", o);
            queryBuilder.append(" OFFSET :offset ");
        });

        Optional.ofNullable(criteria.limit()).ifPresent(l -> {
            params.put("limit", l);
            queryBuilder.append(" LIMIT :limit");
        });

        return jdbcTemplate.queryForStream(queryBuilder.toString(), params, mapper).toList();
    }

    public Optional<QuestionAnswer> findById(ID<QuestionAnswer> id) {
        var query = """
                    SELECT a.id as id, a.created_at as createdAt, a.answers as answers,
                           s.id as student, q.id as question, q.type as type
                    FROM question_answer AS a
                    INNER JOIN(
                        SELECT key, id, 'choose-a-word' as type FROM chooseaword_content
                        UNION ALL
                        SELECT key, id, 'fill-a-word' as type FROM fillaword_content
                    ) AS q ON a.question = q.key
                    INNER JOIN
                    app_user s on a.student = s.key
                    WHERE a.id = :id
                    """;

        var parameters = new MapSqlParameterSource().addValue("id", id.id());

        return jdbcTemplate.queryForStream(query, parameters, mapper)
                .findFirst();
    }

    private static final RowMapper<QuestionAnswer> mapper = (r, i) -> {
        var idString = r.getString("id");
        var questionTypeString = r.getString("type");
        var questionIdString = r.getString("question");
        var studentIdString = r.getString("student");
        var answersString = r.getString("answers");
        var createdAt = r.getTimestamp("createdAt");

        return map(idString, questionIdString, questionTypeString, studentIdString, answersString, createdAt);
    };

    private static QuestionAnswer map(
            String idString, String questionIdString, String questionTypeString,
            String studentIdString, String answersString, Timestamp createdAt) {

        var id = new ID<>(QuestionAnswer.class, idString);
        var questionType = questionTypeFromString(questionTypeString);
        var questionId = new ID<>(questionType, questionIdString);
        var studentId = new ID<>(Student.class, studentIdString);
        var answers = Converter.FromDatabase.stringList(answersString);

        return new QuestionAnswer(
                id, questionId, studentId, answers, createdAt
        );
    }

    private static Class<? extends Question> questionTypeFromString(String questionTypeString) {
        return switch (questionTypeString) {
            case "choose-a-word" -> ChooseAWordQuestion.class;
            default -> throw new IllegalStateException("No question type found for " + questionTypeString);
        };
    }

}
