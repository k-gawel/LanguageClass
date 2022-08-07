package event.events;

import model.domain.ID;
import model.domain.evaluation.ExerciseEvaluation;
import model.domain.user.AppUser;
import model.input.ExerciseEvaluationInput;

import java.util.List;

public class ExerciseEvaluationEvent extends Event<ExerciseEvaluationInput, ExerciseEvaluation>  {

    public ExerciseEvaluationEvent(ID<AppUser> accessor, ExerciseEvaluationInput input, List<String> errors) {
        super(accessor, input, errors);
    }

    public ExerciseEvaluationEvent(ID<AppUser> accessor, ExerciseEvaluationInput input, ExerciseEvaluation exerciseEvaluation) {
        super(accessor, input, exerciseEvaluation);
    }

}
