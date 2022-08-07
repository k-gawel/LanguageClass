package event.events;

import model.domain.ID;
import model.domain.answer.ExerciseAnswer;
import model.domain.user.AppUser;
import model.input.ExerciseAnswerInput;

import java.util.List;

public class ExerciseAnswerEvent extends Event<ExerciseAnswerInput, ExerciseAnswer> {

    public ExerciseAnswerEvent(ID<AppUser> accessor, ExerciseAnswerInput input, ExerciseAnswer answer) {
        super(accessor, input, answer);
    }

    public ExerciseAnswerEvent(ID<AppUser> accessor, ExerciseAnswerInput input, List<String> errors) {
        super(accessor, input, errors);
    }

}
