package language.graphql.answerandevaluation.binder;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.repository.answerandevalution.QuestionAnswerRepository;
import language.graphql.answerandevaluation.input.QuestionAnswerInput;
import language.service.service.answerandevaluation.services.QuestionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class ExerciseAnswerInputBinderHelper {

    private final CreateQuestionAnswerBinder createQuestionAnswerBinder;
    private final QuestionAnswerService questionAnswerService;
    private final QuestionAnswerRepository questionAnswerRepository;


    public List<QuestionAnswer> getQuestionAnswers(Exercise exercise, List<QuestionAnswerInput> inputs, List<String> ids) {
        var fromInputs = getQuestionAnswersFromInputs(inputs);
        var fromIds = getQuestionAnswersFromIds(ids);
        return getQuestionAnswers(exercise, fromInputs, fromIds);
    }

    private List<QuestionAnswer> getQuestionAnswers(Exercise exercise, Map<DomainID<Question>, QuestionAnswer> fromInputs, Map<DomainID<Question>, QuestionAnswer> fromIds) {
        return exercise.questions().stream()
                .map(q -> getQuestionAnswer(q, fromInputs, fromIds))
                .toList();
    }

    private Map<DomainID<Question>, QuestionAnswer> getQuestionAnswersFromInputs(List<QuestionAnswerInput> inputs) {
        return inputs.stream()
                .map(createQuestionAnswerBinder::bind)
                .map(questionAnswerService::create)
                .collect(Collectors.toMap(QuestionAnswer::question, q -> q));
    }

    private Map<DomainID<Question>, QuestionAnswer> getQuestionAnswersFromIds(List<String> ids) {
        return ids.stream()
                .map(questionAnswerRepository::getById)
                .collect(Collectors.toMap(QuestionAnswer::question, q -> q));
    }

    private QuestionAnswer getQuestionAnswer(DomainID<Question> question,
                                             Map<DomainID<Question>, QuestionAnswer> fromInputs,
                                             Map<DomainID<Question>, QuestionAnswer> fromIds) {
        return fromInputs.containsKey(question) ? fromInputs.get(question) : fromIds.get(question);
    }


}
