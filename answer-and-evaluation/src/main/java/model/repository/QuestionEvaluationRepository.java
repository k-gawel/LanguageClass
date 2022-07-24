package model.repository;

import model.domain.ID;
import model.domain.QuestionAnswer;
import model.domain.QuestionEvaluation;
import model.domain.Teacher;
import model.repository.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.Optional;

@Repository
public class QuestionEvaluationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionEvaluationRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Optional<QuestionEvaluation> findById(ID<QuestionEvaluation> id) {
        var query = """
                    SELECT e.id as id, e.comments, a.id as answer_id,
                    u.id as teacher_di, '' as comments, null as created_at
                    FROM question_evaluation e
                    INNER JOIN app_user AS u ON e.teacher = u.key
                    INNER JOIN question_answer a ON e.answer = a.key
                    """;
        var parameters = new MapSqlParameterSource().addValue("id", id.id());

        return jdbcTemplate.queryForStream(query, parameters, mapper)
                .findFirst();
    }

    private static final RowMapper<QuestionEvaluation> mapper = (r, i) -> map(
            r.getString("id"),
            r.getString("answer_id"),
            r.getString("teacher_id"),
            r.getString("comments"),
            r.getInt("score")
    );

    private static QuestionEvaluation map(
            String idString, String answerIdString, String teacherIdString, String commentsString, int score) {
        return new QuestionEvaluation(
                new ID<>(QuestionEvaluation.class, idString),
                new ID<>(QuestionAnswer.class, answerIdString),
                new ID<>(Teacher.class, teacherIdString),
                Converter.FromDatabase.stringList(commentsString),
                score,
                new Date()
        );
    }


}
