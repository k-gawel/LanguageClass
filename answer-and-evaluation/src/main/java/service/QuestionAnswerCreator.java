package service;

import model.domain.ID;
import model.domain.answer.QuestionAnswer;
import model.input.QuestionAnswerInput;
import model.repository.QuestionRepository;
import model.repository.UserRepository;
import model.repository.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Service
public class QuestionAnswerCreator extends Creator {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionAnswerCreator(NamedParameterJdbcTemplate jdbcTemplate, UserRepository userRepository, QuestionRepository questionRepository) {
        super("question_answer", jdbcTemplate);
        this.questionRepository = questionRepository;
    }

    public QuestionAnswer createAndSave(QuestionAnswerInput input) {
        var answer = create(input);
        save(answer);
        return answer;
    }

    public QuestionAnswer create(QuestionAnswerInput input) {
        var id = getId(getBaseId(input));
        return new QuestionAnswer(
                new ID<>(QuestionAnswer.class, id),
                input.question(),
                input.author(),
                input.answers(),
                new Timestamp(new Date().getTime())
        );
    }

    public void save(QuestionAnswer answer) {
        var studentKey = findKeyById("app_user", answer.student().id()).orElseThrow();
        var questionKey = questionRepository.findKey(answer.question()).orElseThrow();
        var answers = Converter.ToDatabase.stringList(answer.answers());

        var sql = "INSERT INTO question_answer (id, question, student, answers) " +
                  "VALUES (:id, :question, :student, :answers)";

        var params = Map.of(
                "id", answer.id().id(),
                "student", studentKey,
                "answers", answers,
                "question", questionKey
        );

        jdbcTemplate.update(sql, params);
    }

    private String getBaseId(QuestionAnswerInput input) {
        return "answer_for_" + input.question().id() + "_by_" + input.author().id();
    }




}
