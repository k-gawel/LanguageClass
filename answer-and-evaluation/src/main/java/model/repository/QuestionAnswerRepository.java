package model.repository;

import model.domain.*;
import model.repository.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Repository
public class QuestionAnswerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final QuestionRepository questionRepository;
    private final String tableName = "question_answer";

    @Autowired
    public QuestionAnswerRepository(DataSource dataSource, QuestionRepository questionRepository) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.questionRepository = questionRepository;
    }


    public Optional<Long> findKey(ID<QuestionAnswer> answer) {
        var sql = "SELECT key FROM " + tableName + " WHERE id = :id";
        var params = Map.of("id", answer.id());
        return jdbcTemplate.queryForStream(sql, params, (r, i) -> r.getLong("key")).findFirst();
    }

    public Optional<QuestionAnswer> findById(ID<QuestionAnswer> id) {
        var query = """
                    SELECT a.id as id, null as created_at, a.answers as answers,
                           s.id as student, q.id as question, q.type as type
                    FROM question_answer AS a
                    INNER JOIN(
                        SELECT key, id, 'choose-a-word' as type FROM chooseaword_content
                        UNION ALL
                        SELECT key, id, 'fill-a-word' as type FROM fillaword_content
                    ) AS q ON a.question = q.key
                    INNER JOIN
                    app_user s on a.student = s.key
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
        var createdAt = r.getDate("created_at");

        return map(idString, questionIdString, questionTypeString, studentIdString, answersString, createdAt);
    };

    private static QuestionAnswer map(
            String idString, String questionIdString, String questionTypeString,
            String studentIdString, String answersString, Date createdAt) {

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
