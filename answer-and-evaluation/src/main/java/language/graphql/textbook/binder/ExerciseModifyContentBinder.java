package language.graphql.textbook.binder;

import language.contentandrepository.repository.question.QuestionRepository;
import language.contentandrepository.repository.textbook.ExerciseRepository;
import language.graphql.shared.InputBinder;
import language.graphql.textbook.input.ModifyContentInput;
import language.service.service.textbook.inputs.ExerciseModifyContentInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseModifyContentBinder implements InputBinder<ModifyContentInput, ExerciseModifyContentInput> {

    private final ExerciseRepository exerciseRepository;
    private final QuestionRepository questionRepository;

    @Override
    public ExerciseModifyContentInput bind(ModifyContentInput source) {
        return new ExerciseModifyContentInput(
                exerciseRepository.findById(source.containerId()).orElse(null),
                questionRepository.findById(source.contentId()).orElse(null),
                source.newPlace()
        );
    }

    @Override
    public Class<ModifyContentInput> consumes() {
        return ModifyContentInput.class;
    }

    @Override
    public Class<ExerciseModifyContentInput> generates() {
        return ExerciseModifyContentInput.class;
    }
}
