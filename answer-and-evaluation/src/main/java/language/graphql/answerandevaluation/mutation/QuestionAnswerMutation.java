package language.graphql.answerandevaluation.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.graphql.answerandevaluation.binder.*;
import language.graphql.answerandevaluation.input.ExerciseAnswerInput;
import language.graphql.answerandevaluation.input.QuestionAnswerInput;
import language.graphql.answerandevaluation.input.QuestionEvaluationInput;
import language.service.service.answerandevaluation.services.ExerciseAnswerService;
import language.service.service.answerandevaluation.services.QuestionAnswerService;
import language.service.service.answerandevaluation.services.QuestionEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionAnswerMutation implements GraphQLMutationResolver {

    private final QuestionAnswerService questionAnswerService;
    private final QuestionEvaluationService questionEvaluationService;

    private final CreateQuestionEvaluationBinder createQuestionEvaluationBinder;
    private final UpdateQuestionEvaluationInputBinder updateQuestionEvaluationInputBinder;

    private final CreateQuestionAnswerBinder createInputBinder;
    private final QuestionAnswerUpdateInputBinder updateInputBinder;

    private final CreateExerciseAnswerInputBinder createExerciseAnswerInputBinder;
    private final UpdateExerciseAnswerInputBinder updateExerciseAnswerInputBinder;

    private final CreateExerciseEvaluationInputBinder createExerciseEvaluationInputBinder;

    private final ExerciseAnswerService exerciseAnswerService;

    @PreAuthorize("hasAuthority('STUDENT') and authentication.principal.username.equals(#input.author())")
    public QuestionAnswer createQuestionAnswer(QuestionAnswerInput input) {
        var bindedInput = createInputBinder.bind(input);
        return questionAnswerService.create(bindedInput);
    }

    @PreAuthorize("authentication.principal.username.equals(@questionAnswerRepository.getById(#input.id()).student().id())")
    public QuestionAnswer updateQuestionAnswer(QuestionAnswerInput input) {
        var bindedInput = updateInputBinder.bind(input);
        return questionAnswerService.updateQuestionAnswer(bindedInput);
    }

    @PreAuthorize("hasAuthority('TEACHER') AND principal.username.equals(#rawInput.author())")
    public QuestionEvaluation createQuestionEvaluation(QuestionEvaluationInput rawInput) {
        var input = createQuestionEvaluationBinder.bind(rawInput);
        return questionEvaluationService.createQuestionEvaluation(input);
    }

    @PreAuthorize("hasAuthority('TEACHER') AND principal.username.equals(#rawInput.author())")
    public QuestionEvaluation updateQuestionEvaluation(QuestionEvaluationInput rawInput) {
        var input = updateQuestionEvaluationInputBinder.bind(rawInput);
        return questionEvaluationService.updateQuestionEvaluation(input);
    }

    @PreAuthorize("hasAuthority('STUDENT') AND principal.username.equals(#rawInput.author())")
    public ExerciseAnswer createExerciseAnswer(ExerciseAnswerInput rawInput) {
        var input = createExerciseAnswerInputBinder.bind(rawInput);
        return exerciseAnswerService.create(input);
    }

    @PreAuthorize("hasAuthority('STUDENT') AND @exerciseAnswerFilter.canUpdate(principal.username, #rawInput.id)")
    public ExerciseAnswer updateExerciseAnswer(ExerciseAnswerInput rawInput) {
        var input = updateExerciseAnswerInputBinder.bind(rawInput);
        return exerciseAnswerService.update(input);
    }


}
