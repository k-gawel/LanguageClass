package language.graphql.answerandevaluation.binder;

import language.graphql.answerandevaluation.input.ExerciseEvaluationInput;
import language.graphql.shared.InputBinder;
import language.service.service.answerandevaluation.inputs.ExerciseEvaluationCreateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateExerciseEvaluationInputBinder implements InputBinder<ExerciseEvaluationInput, ExerciseEvaluationCreateInput> {

    @Override
    public ExerciseEvaluationCreateInput bind(ExerciseEvaluationInput source) {
//TODO
return null;}

    @Override
    public Class<ExerciseEvaluationInput> consumes() {
        return ExerciseEvaluationInput.class;
    }

    @Override
    public Class<ExerciseEvaluationCreateInput> generates() {
        return ExerciseEvaluationCreateInput.class;
    }

}
