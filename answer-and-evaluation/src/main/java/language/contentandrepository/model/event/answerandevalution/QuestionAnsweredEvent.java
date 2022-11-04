package language.contentandrepository.model.event.answerandevalution;

import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.service.input.QuestionAnswerInput;
import language.contentandrepository.model.domain.user.AppUser;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.event.Event;

import java.util.List;

public class QuestionAnsweredEvent extends Event<QuestionAnswerInput, QuestionAnswer> {

    public QuestionAnsweredEvent(DomainID<AppUser> accessor, QuestionAnswerInput input, QuestionAnswer answer) {
        super(accessor, input, answer);
    }

    public QuestionAnsweredEvent(DomainID<AppUser> accessor, QuestionAnswerInput input, List<String> errors) {
        super(accessor, input, errors);
    }

}
