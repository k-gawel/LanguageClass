package service;

import model.domain.ID;
import model.domain.evaluation.ExerciseEvaluation;
import model.input.ExerciseEvaluationInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ExerciseEvaluationCreator extends Creator {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ExerciseEvaluationCreator(NamedParameterJdbcTemplate jdbcTemplate) {
        super("exercise_evaluation", jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    public ExerciseEvaluation create(ExerciseEvaluationInput input) {
        var baseId = getBaseId(input);
        var id = new ID<>(ExerciseEvaluation.class, getId(baseId));

        return new ExerciseEvaluation(
                id,
                input.author(),
                input.answer(),
                new ArrayList<>(),
                input.comment(),
                input.rating(),
                Timestamp.from(new Date().toInstant())
        );
    }

    void save(ExerciseEvaluation evaluation) {
        var answerKey = findKeyById("exercise_answer", evaluation.answer().id()).orElseThrow();
        var authorKey = findKeyById("app_user", evaluation.author().id()).orElseThrow();

        var sql = "INSERT INTO exercise_evaluation (id, answer, rating, teacher, comment) " +
                                           "VALUES (:id, :answer, :rating, :teacher, :comment)";
        var params = Map.of(
                "id", evaluation.id().id(),
                "answer", answerKey,
                "teacher", authorKey,
                "comment", evaluation.comment(),
                "rating", evaluation.score()
        );
        jdbcTemplate.update(sql, params);
    }

    private String getBaseId(ExerciseEvaluationInput input) {
        return "evaluation_for_" + input.answer().id() + "_by_" + input.author().id();
    }

}
