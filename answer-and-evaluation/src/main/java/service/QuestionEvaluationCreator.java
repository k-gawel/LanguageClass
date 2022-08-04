package service;

import model.domain.ID;
import model.domain.evaluation.QuestionEvaluation;
import model.input.QuestionEvaluationInput;
import model.repository.QuestionAnswerRepository;
import model.repository.UserRepository;
import model.repository.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Clock;
import java.util.Date;
import java.util.Map;

@Service
public class QuestionEvaluationCreator extends Creator{

    private final QuestionAnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final Clock clock;

    @Autowired
    protected QuestionEvaluationCreator(NamedParameterJdbcTemplate jdbcTemplate, QuestionAnswerRepository answerRepository, UserRepository userRepository, Clock clock) {
        super("question_evaluation", jdbcTemplate);
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.clock = clock;
    }

    public QuestionEvaluation create(QuestionEvaluationInput input) {
        var idString = getId(getBaseId(input));
        var id = new ID<>(QuestionEvaluation.class, idString);

        return new QuestionEvaluation(id,
                input.answer(),
                input.author(),
                input.comments(),
                input.score(),
                new Timestamp(clock.millis())
        );
    }

    private void save(QuestionEvaluation evaluation) {
        var answerKey = answerRepository.findKey(evaluation.answer()).orElseThrow();
        var authorKey = userRepository.findKey(evaluation.author()).orElseThrow();
        var comments = Converter.ToDatabase.stringList(evaluation.comments());

        var sql = "INSERT INTO question_evaluation (id, rating, answer, comments, teacher) " +
                                           "VALUES (:id, :rating, :answer, :comments, :teacher)";

        var params = Map.of(
                "id", evaluation.id().id(),
                "rating", evaluation.score(),
                "answer", answerKey,
                "teacher", authorKey,
                "comments", comments
        );
        jdbcTemplate.update(sql, params);
    }

    private String getBaseId(QuestionEvaluationInput input) {
        return "evaluation_for_" + input.answer().id() + "_by_" + input.author().id();
    }


}
