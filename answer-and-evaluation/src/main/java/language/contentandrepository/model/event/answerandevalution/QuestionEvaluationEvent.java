package language.contentandrepository.model.event.answerandevalution;

import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.model.event.Event;
import language.service.input.QuestionEvaluationInput;
import language.contentandrepository.model.domain.user.AppUser;
import language.contentandrepository.model.DomainID;

import java.util.List;

public class QuestionEvaluationEvent extends Event<QuestionEvaluationInput, QuestionEvaluation> {

    public QuestionEvaluationEvent(DomainID<AppUser> accessor, QuestionEvaluationInput questionEvaluationInput, QuestionEvaluation questionEvaluation) {
        super(accessor, questionEvaluationInput, questionEvaluation);
    }

    public QuestionEvaluationEvent(DomainID<AppUser> accessor, QuestionEvaluationInput questionEvaluationInput, List<String> errors) {
        super(accessor, questionEvaluationInput, errors);
    }


}
