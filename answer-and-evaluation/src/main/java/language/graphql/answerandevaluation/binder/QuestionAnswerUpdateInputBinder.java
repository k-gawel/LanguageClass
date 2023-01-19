package language.graphql.answerandevaluation.binder;

import language.contentandrepository.repository.answerandevalution.QuestionAnswerRepository;
import language.graphql.shared.InputBinder;
import language.graphql.answerandevaluation.input.QuestionAnswerInput;
import language.service.service.answerandevaluation.inputs.QuestionAnswerUpdateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionAnswerUpdateInputBinder implements InputBinder<QuestionAnswerInput, QuestionAnswerUpdateInput> {

    private final QuestionAnswerRepository questionAnswerRepository;

    @Override
    public QuestionAnswerUpdateInput bind(QuestionAnswerInput source) {
        return new QuestionAnswerUpdateInput(
                questionAnswerRepository.findById(source.id()).orElseGet(null),
                source.answers()
        );
    }

    @Override
    public Class<QuestionAnswerInput> consumes() {
        return QuestionAnswerInput.class;
    }

    @Override
    public Class<QuestionAnswerUpdateInput> generates() {
        return QuestionAnswerUpdateInput.class;
    }
}
