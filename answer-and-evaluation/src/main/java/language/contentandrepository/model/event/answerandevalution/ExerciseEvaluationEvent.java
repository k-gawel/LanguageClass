package language.contentandrepository.model.event.answerandevalution;

import language.contentandrepository.model.domain.answerandevaluation.ExerciseEvaluation;
import language.graphql.answerandevaluation.input.ExerciseEvaluationInput;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.AppUser;
import language.contentandrepository.model.event.Event;

import java.util.List;

public class ExerciseEvaluationEvent extends Event<ExerciseEvaluationInput, ExerciseEvaluation> {

    public ExerciseEvaluationEvent(DomainID<AppUser> accessor, ExerciseEvaluationInput input, List<String> errors) {
        super(accessor, input, errors);
    }

    public ExerciseEvaluationEvent(DomainID<AppUser> accessor, ExerciseEvaluationInput input, ExerciseEvaluation exerciseEvaluation) {
        super(accessor, input, exerciseEvaluation);
    }

}
