package service;

import lombok.AllArgsConstructor;
import model.domain.ID;
import model.domain.evaluation.ExerciseEvaluation;
import model.input.ExerciseEvaluationInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Clock;
import java.util.*;

@Service
public class ExerciseEvaluationCreator extends Creator {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final Clock clock;

    @Autowired
    public ExerciseEvaluationCreator(NamedParameterJdbcTemplate jdbcTemplate, Clock clock) {
        super("exercise_evaluation", jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
        this.clock = clock;
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
                new Timestamp(clock.millis())
        );
    }

    void save(ExerciseEvaluation evaluation) {
        var answerKey = findKeyById("exercise_answer", evaluation.answer().id()).orElseThrow();
        var authorKey = findKeyById("app_user", evaluation.author().id()).orElseThrow();

        var sql = "INSERT INTO exercise_evaluation (id, answer, rating, teacher, comment, created_at) " +
                                           "VALUES (:id, :answer, :rating, :teacher, :comment, :createdAt)";
        var params = Map.of(
                "id", evaluation.id().id(),
                "answer", answerKey,
                "teacher", authorKey,
                "comment", evaluation.comment(),
                "rating", evaluation.score(),
                "createdAt", evaluation.createdAt()
        );
        jdbcTemplate.update(sql, params);
    }

    private String getBaseId(ExerciseEvaluationInput input) {
        return "evaluation_for_" + input.answer().id() + "_by_" + input.author().id();
    }

}
