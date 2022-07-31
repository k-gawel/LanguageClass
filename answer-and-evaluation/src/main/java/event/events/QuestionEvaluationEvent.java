package event.events;

import model.domain.user.AppUser;
import model.domain.ID;
import model.domain.evaluation.QuestionEvaluation;
import model.input.QuestionEvaluationInput;

import java.util.List;

public class QuestionEvaluationEvent extends Event<QuestionEvaluationInput, QuestionEvaluation> {

    public QuestionEvaluationEvent(ID<AppUser> accessor, QuestionEvaluationInput questionEvaluationInput, QuestionEvaluation questionEvaluation) {
        super(accessor, questionEvaluationInput, questionEvaluation);
    }

    public QuestionEvaluationEvent(ID<AppUser> accessor, QuestionEvaluationInput questionEvaluationInput, List<String> errors) {
        super(accessor, questionEvaluationInput, errors);
    }


}
