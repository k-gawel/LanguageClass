package language.graphql.answerandevaluation.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.graphql.answerandevaluation.binder.CreateQuestionAnswerBinder;
import language.graphql.answerandevaluation.binder.QuestionAnswerUpdateInputBinder;
import language.graphql.answerandevaluation.input.QuestionAnswerInput;
import language.service.service.answerandevaluation.services.QuestionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionAnswerMutation implements GraphQLMutationResolver {

    private final QuestionAnswerService questionAnswerService;

    private final CreateQuestionAnswerBinder createInputBinder;
    private final QuestionAnswerUpdateInputBinder updateInputBinder;

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

}
