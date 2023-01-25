package language.graphql.answerandevaluation.binder;

import language.contentandrepository.repository.answerandevalution.QuestionEvaluationRepository;
import language.graphql.answerandevaluation.input.QuestionEvaluationInput;
import language.graphql.shared.InputBinder;
import language.service.service.answerandevaluation.inputs.QuestionEvaluationUpdateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateQuestionEvaluationInputBinder implements InputBinder<QuestionEvaluationInput, QuestionEvaluationUpdateInput> {

    private final QuestionEvaluationRepository questionEvaluationRepository;

    @Override
    public QuestionEvaluationUpdateInput bind(QuestionEvaluationInput source) {
        return new QuestionEvaluationUpdateInput(
                questionEvaluationRepository.getById(source.id()),
                source.comments(),
                source.score()
        );
    }

    @Override
    public Class<QuestionEvaluationInput> consumes() {
        return QuestionEvaluationInput.class;
    }

    @Override
    public Class<QuestionEvaluationUpdateInput> generates() {
        return QuestionEvaluationUpdateInput.class;
    }
}
