package model.repository;

import model.domain.*;
import model.domain.answer.QuestionAnswer;
import model.domain.content.ChooseAWordQuestion;
import model.domain.content.Question;
import model.domain.user.Student;
import model.repository.content.QuestionRepository;
import model.repository.criteria.QuestionAnswerCriteria;
import model.repository.utils.Converter;
import org.jooq.tools.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class QuestionAnswerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String tableName = "question_answer";


    @Autowired
    public QuestionAnswerRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, QuestionRepository questionRepository) {
        jdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<ID<QuestionAnswer>> findId(String id) {
        return jdbcTemplate.queryForStream(
                "SELECT COUNT(*) as count FROM question_answer WHERE id = :id",
                    Map.of("id", id),
                    (r, i) -> r.getLong("count")
                )
                .filter(i -> i == 1)
                .map(i -> new ID<>(QuestionAnswer.class, id))
                .findAny();
    }

    public Optional<Long> findKey(ID<QuestionAnswer> answer) {
        var sql = "SELECT key FROM " + tableName + " WHERE id = :id";
        var params = Map.of("id", answer.id());
        return jdbcTemplate.queryForStream(sql, params, (r, i) -> r.getLong("key")).findFirst();
    }

    public List<QuestionAnswer> findByCriteria(QuestionAnswerCriteria criteria) {
        var query = SELECT
                    + getConditions(criteria)
                    + (criteria.limit() != null ? " LIMIT :limit " : "")
                    + (criteria.offset() != null ? " OFFSET :offset " : "");

        return jdbcTemplate.queryForStream(query, criteria, mapper).toList();
    }

    public Optional<QuestionAnswer> findById(ID<QuestionAnswer> id) {
        var criteria = QuestionAnswerCriteria.builder().ids(List.of(id.id())).build();
        return findByCriteria(criteria).stream().findFirst();
    }

    private final String SELECT = """
                                    SELECT a.id as id, a.created_at as createdAt, a.answers as answers,
                                           s.id as student, q.id as question, q.type as type
                                    FROM question_answer AS a
                                    INNER JOIN(
                                        SELECT key, id, 'choose-a-word' as type FROM chooseaword_content
                                        UNION ALL
                                        SELECT key, id, 'fill-a-word' as type FROM fillaword_content
                                    ) AS q ON a.question = q.key
                                    INNER JOIN
                                    app_user s ON a.student = s.key
                                  """;

    private String getConditions(QuestionAnswerCriteria criteria) {
        return " WHERE " + Stream.of(
                (criteria.ids() != null ? "a.id IN (:ids)" : ""),
                (criteria.questions() != null ? "q.id IN (:questions)" : ""),
                (criteria.students() != null ? "s.id IN (:students)" : ""),
                (criteria.startDate() != null ? "a.created_at > :startDate" : ""),
                (criteria.endDate() != null ? "a.created_at < :endDate" : "")
        ).filter(s -> !StringUtils.isBlank(s)).collect(Collectors.joining(" AND "));
    }

    private static final RowMapper<QuestionAnswer> mapper = (r, i) -> {
        return new QuestionAnswer(
                new ID<>(QuestionAnswer.class, r.getString("id")),
                new ID<>(questionTypeFromString(r.getString("type")), r.getString("question")),
                new ID<>(Student.class, r
                        .getString("student")),
                Converter.FromDatabase.stringList(r.getString("answers")),
                r.getTimestamp("createdAt")
        );
    };

    private static Class<? extends Question> questionTypeFromString(String questionTypeString) {
        return switch (questionTypeString) {
            case "choose-a-word" -> ChooseAWordQuestion.class;
            default -> throw new IllegalStateException("No question type found for " + questionTypeString);
        };
    }

}
