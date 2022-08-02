package event.events;

import model.domain.user.AppUser;
import model.domain.ID;
import model.domain.answer.QuestionAnswer;
import model.input.QuestionAnswerInput;

import java.util.List;

public class QuestionAnsweredEvent extends Event<QuestionAnswerInput, QuestionAnswer>  {

    public QuestionAnsweredEvent(ID<AppUser> accessor, QuestionAnswerInput input, QuestionAnswer answer) {
        super(accessor, input, answer);
    }

    public QuestionAnsweredEvent(ID<AppUser> accessor, QuestionAnswerInput input, List<String> errors) {
        super(accessor, input, errors);
    }

}
