package service;

import model.domain.ID;
import model.domain.QuestionAnswer;
import model.domain.QuestionEvaluation;
import model.domain.Teacher;
import model.input.QuestionEvaluationInput;
import model.repository.QuestionAnswerRepository;
import model.repository.UserRepository;
import model.repository.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class QuestionEvaluationCreator extends Creator{

    private final QuestionAnswerRepository answerRepository;
    private final UserRepository userRepository;

    @Autowired
    protected QuestionEvaluationCreator(String tableName, NamedParameterJdbcTemplate jdbcTemplate, QuestionAnswerRepository answerRepository, UserRepository userRepository) {
        super(tableName, jdbcTemplate);
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    public QuestionEvaluation create(QuestionEvaluationInput input) {
        var answerKey = answerRepository.findKey(input.answer()).orElseThrow();
        var authorKey = userRepository.findKey(input.author()).orElseThrow();
        var id = new ID<>(QuestionEvaluation.class, getId(input.baseId()));
        var comments = Converter.ToDatabase.stringList(input.comments());

        saveInDatabase(id.id(), input.score(), answerKey, authorKey, comments);

        return create(id, input.author(), input.answer(), input.comments(), input.score());
    }

    private QuestionEvaluation create(ID<QuestionEvaluation> id, ID<Teacher> author, ID<QuestionAnswer> answer, List<String> comments, int score) {
        return new QuestionEvaluation(
                id, answer, author, comments, score, new Date()
        );
    }


    private void saveInDatabase(String id, int rating, long answerKey, long teacherKey, String comments) {
        var sql = "INSERT INTO question_evaluation (id, rating, answer, comments, teacher) " +
                  "VALUES (:id, :rating, :answer, :comments, :teacher)";
        var params = Map.of(
                "id", id, "rating", rating, "answer", answerKey, "teacher", teacherKey, "comments", comments
        );
        jdbcTemplate.update(sql, params);
    }


}
