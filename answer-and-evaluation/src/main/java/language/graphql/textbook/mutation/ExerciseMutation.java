package language.graphql.textbook.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.graphql.textbook.binder.ExerciseCreateInputBinder;
import language.graphql.textbook.binder.ExerciseModifyContentBinder;
import language.graphql.textbook.binder.ExerciseUpdateInputBinder;
import language.graphql.textbook.input.ExerciseInput;
import language.graphql.textbook.input.ModifyContentInput;
import language.service.service.textbook.services.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('TEACHER')")
public class ExerciseMutation implements GraphQLMutationResolver {

    private final ExerciseService exerciseService;
    private final ExerciseCreateInputBinder exerciseCreateInputBinder;
    private final ExerciseUpdateInputBinder exerciseUpdateInputBinder;
    private final ExerciseModifyContentBinder exerciseModifyContentBinder;

    public Exercise createExercise(ExerciseInput rawInput) {
        var input = exerciseCreateInputBinder.bind(rawInput);
        return exerciseService.createExercise(input);
    }

    public Exercise updateExercise(ExerciseInput rawInput) {
        var input = exerciseUpdateInputBinder.bind(rawInput);
        return exerciseService.updateExercise(input);
    }

    public List<String> addExerciseContent(String exercise, String content) {
        var input = new ModifyContentInput(exercise, content, Integer.MAX_VALUE);
        return modifyContentAndGetIds(input);
    }

    public List<String> removeExerciseContent(String exercise, String content) {
        var input = new ModifyContentInput(exercise, content, Integer.MIN_VALUE);
        return modifyContentAndGetIds(input);
    }

    public List<String> moveExerciseContent(String exercise, String content, int newPlace) {
        var input = new ModifyContentInput(exercise, content, newPlace);
        return modifyContentAndGetIds(input);
    }



    private List<String> modifyContentAndGetIds(ModifyContentInput rawInput) {
        var input = exerciseModifyContentBinder.bind(rawInput);
        return exerciseService
                .modifyContent(input)
                .stream()
                .map(DomainID::id)
                .toList();
    }


}
