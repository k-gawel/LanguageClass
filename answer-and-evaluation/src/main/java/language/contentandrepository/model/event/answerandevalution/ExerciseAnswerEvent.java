package language.contentandrepository.model.event.answerandevalution;

import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.graphql.answerandevaluation.input.ExerciseAnswerInput;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.AppUser;
import language.contentandrepository.model.event.Event;

import java.util.List;

public class ExerciseAnswerEvent extends Event<ExerciseAnswerInput, ExerciseAnswer> {

    public ExerciseAnswerEvent(DomainID<AppUser> accessor, ExerciseAnswerInput input, ExerciseAnswer answer) {
        super(accessor, input, answer);
    }

    public ExerciseAnswerEvent(DomainID<AppUser> accessor, ExerciseAnswerInput input, List<String> errors) {
        super(accessor, input, errors);
    }

}
