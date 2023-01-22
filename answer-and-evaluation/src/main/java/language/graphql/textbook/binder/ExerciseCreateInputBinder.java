package language.graphql.textbook.binder;

import language.contentandrepository.repository.textbook.ExerciseRepository;
import language.contentandrepository.repository.user.TeacherRepository;
import language.graphql.shared.InputBinder;
import language.graphql.textbook.input.ExerciseInput;
import language.service.service.textbook.inputs.ExerciseCreateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseCreateInputBinder implements InputBinder<ExerciseInput, ExerciseCreateInput> {

    private final ExerciseRepository exerciseRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public ExerciseCreateInput bind(ExerciseInput source) {
        return new ExerciseCreateInput(
                teacherRepository.findById(source.author()).orElse(null),
                source.title(),
                source.type()
        );
    }

    @Override
    public Class<ExerciseInput> consumes() {
        return ExerciseInput.class;
    }

    @Override
    public Class<ExerciseCreateInput> generates() {
        return ExerciseCreateInput.class;
    }
}
