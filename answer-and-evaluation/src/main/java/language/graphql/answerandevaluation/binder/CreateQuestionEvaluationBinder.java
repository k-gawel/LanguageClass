package language.graphql.answerandevaluation.binder;

import language.contentandrepository.repository.answerandevalution.QuestionAnswerRepository;
import language.contentandrepository.repository.user.TeacherRepository;
import language.graphql.answerandevaluation.input.QuestionEvaluationInput;
import language.graphql.shared.InputBinder;
import language.service.service.answerandevaluation.inputs.QuestionEvaluationCreateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateQuestionEvaluationBinder implements InputBinder<QuestionEvaluationInput, QuestionEvaluationCreateInput> {

    private final QuestionAnswerRepository questionAnswerRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public QuestionEvaluationCreateInput bind(QuestionEvaluationInput source) {
        return new QuestionEvaluationCreateInput(
                questionAnswerRepository.getById(source.answer()),
                teacherRepository.getById(source.author()),
                source.comments(),
                source.score()
        );
    }

    @Override
    public Class<QuestionEvaluationInput> consumes() {
        return QuestionEvaluationInput.class;
    }

    @Override
    public Class<QuestionEvaluationCreateInput> generates() {
        return QuestionEvaluationCreateInput.class;
    }

}
