package language.graphql.textbook.binder;

import language.contentandrepository.repository.textbook.ExerciseRepository;
import language.graphql.shared.InputBinder;
import language.graphql.textbook.input.ExerciseInput;
import language.service.service.textbook.inputs.ExerciseUpdateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseUpdateInputBinder implements InputBinder<ExerciseInput, ExerciseUpdateInput> {

    private final ExerciseRepository exerciseRepository;

    @Override
    public ExerciseUpdateInput bind(ExerciseInput source) {
        return new ExerciseUpdateInput(
                exerciseRepository.findById(source.id()).orElse(null),
                source.title(),
                source.type()
        );
    }

    @Override
    public Class<ExerciseInput> consumes() {
        return ExerciseInput.class;
    }

    @Override
    public Class<ExerciseUpdateInput> generates() {
        return ExerciseUpdateInput.class;
    }
}
