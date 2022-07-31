package model.input.parser;

import model.domain.ID;
import model.domain.content.Question;
import model.domain.user.Student;
import model.input.QuestionAnswerInput;
import model.repository.QuestionRepository;
import model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAnswerInputParser {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public QuestionAnswerInputParser(UserRepository userRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public QuestionAnswerInput parse(String questionId, String studentId, List<String> answers) {
        var question = getQuestionId(questionId);
        var student = getStudentId(studentId);

        return new QuestionAnswerInput(question, student, answers);
    }

    private ID<Student> getStudentId(String id) {
        return userRepository
                .findById(new ID<>(Student.class, id))
                .map(r -> new ID<>(Student.class, id))
                .orElseThrow();
    }

    private ID<? extends Question> getQuestionId(String id) {
        return questionRepository
                .findQuestionType(id)
                .map(t -> new ID<>(t, id))
                .filter(i -> questionRepository.findById(i).isPresent())
                .orElseThrow();
    }


}
