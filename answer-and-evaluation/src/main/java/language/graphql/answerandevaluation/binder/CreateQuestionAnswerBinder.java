package language.graphql.answerandevaluation.binder;

import language.contentandrepository.repository.question.QuestionRepository;
import language.contentandrepository.repository.user.StudentRepository;
import language.graphql.shared.InputBinder;
import language.graphql.answerandevaluation.input.QuestionAnswerInput;
import language.service.service.answerandevaluation.inputs.QuestionAnswerCreateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateQuestionAnswerBinder implements InputBinder<QuestionAnswerInput, QuestionAnswerCreateInput> {

    private final QuestionRepository questionRepository;
    private final StudentRepository studentRepository;

    @Override
    public QuestionAnswerCreateInput bind(QuestionAnswerInput source) {
        return new QuestionAnswerCreateInput(
                questionRepository.findById(source.question()).orElse(null),
                studentRepository.findById(source.author()).orElse(null),
                source.answers()
        );
    }

    @Override
    public Class<QuestionAnswerInput> consumes() {
        return QuestionAnswerInput.class;
    }

    @Override
    public Class<QuestionAnswerCreateInput> generates() {
        return QuestionAnswerCreateInput.class;
    }

}
