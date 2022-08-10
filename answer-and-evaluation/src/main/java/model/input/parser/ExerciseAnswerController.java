package model.input.parser;

import lombok.AllArgsConstructor;
import model.domain.ID;
import model.domain.answer.ExerciseAnswer;
import model.domain.answer.QuestionAnswer;
import model.domain.content.Question;
import model.domain.user.Student;
import model.input.ExerciseAnswerInput;
import model.input.QuestionAnswerInput;
import model.repository.QuestionAnswerRepository;
import model.repository.content.ExerciseRepository;
import model.repository.content.UserRepository;
import org.springframework.stereotype.Component;
import service.ExerciseAnswerCreator;
import service.QuestionAnswerCreator;

import java.util.HashMap;
import java.util.List;

@Component
@AllArgsConstructor
public class ExerciseAnswerController {

    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final QuestionAnswerCreator questionAnswerCreator;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final QuestionAnswerInputParser questionAnswerInputParser;
    private final ExerciseAnswerCreator exerciseAnswerCreator;

    public ExerciseAnswer create(String exerciseId, String authorId, List<String> answerIds, List<QuestionAnswerInput> answerInputs) {
        var input = new ExerciseAnswerInput(
                exerciseRepository.getId(exerciseId).orElseThrow(),
                getAuthorId(authorId),
                getAnswers(answerIds, answerInputs)
        );

        return exerciseAnswerCreator.create(input);
    }

    private ID<Student> getAuthorId(String idString) {
        return userRepository.findId(idString)
                .filter(t -> t.type().equals(Student.class))
                .map(i -> (ID<Student>) i)
                .orElseThrow();
    }

    private List<ID<QuestionAnswer>> getAnswers(List<String> ids, List<QuestionAnswerInput> inputs) {
        var fromIds = ids.stream().map(i -> new ID<>(QuestionAnswer.class, i))
                .map(i -> questionAnswerRepository.findById(i).orElseThrow())
                .toList();
        var fromInputs = inputs.stream().map(questionAnswerCreator::create).toList();

        return getQuestionAnswers(fromIds, fromInputs);
    }

    private List<ID<QuestionAnswer>> getQuestionAnswers(List<QuestionAnswer> fromIds, List<QuestionAnswer> fromInputs) {
        var map = new HashMap<ID<? extends Question>, ID<QuestionAnswer>>();
        fromInputs.forEach(i -> map.putIfAbsent(i. question(), i.id()));
        fromIds.forEach(i -> map.putIfAbsent(i.question(), i.id()));
        return map.values().stream().toList();
    }



}
