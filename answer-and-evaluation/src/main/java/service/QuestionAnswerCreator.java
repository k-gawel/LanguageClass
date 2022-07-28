package service;

import model.domain.ID;
import model.domain.Question;
import model.domain.QuestionAnswer;
import model.domain.Student;
import model.input.QuestionAnswerInput;
import model.repository.QuestionRepository;
import model.repository.UserRepository;
import model.repository.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionAnswerCreator extends Creator {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionAnswerCreator(NamedParameterJdbcTemplate jdbcTemplate, UserRepository userRepository, QuestionRepository questionRepository) {
        super("question_answer", jdbcTemplate);
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public QuestionAnswer create(QuestionAnswerInput input) {
        var studentKey = userRepository.findKey(input.author())
                .orElseThrow();
        var questionKey = questionRepository.findKey(input.question())
                .orElseThrow();
        var answer = Converter.ToDatabase.stringList(input.answers());
        var id = getId(input.baseId());

        saveInDatabase(id, questionKey, studentKey, answer);

        return create(id, input.question(), input.author(), input.answers());
    }

    private QuestionAnswer create(String id, ID<? extends Question> question, ID<Student> student, List<String> answers) {
        return new QuestionAnswer(
                new ID<>(QuestionAnswer.class, id),
                question,
                student,
                answers,
                new Date()
        );
    }

    private void saveInDatabase(String id, Long questionKey, Long studentKey, String answers) {
        var sql = "INSERT INTO question_answer (id, question, student, answers) " +
                  "VALUES (:id, :question, :student, :answers)";
        var params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("student", studentKey)
                .addValue("answers", answers)
                .addValue("question", questionKey);
        jdbcTemplate.update(sql, params);
    }




}
