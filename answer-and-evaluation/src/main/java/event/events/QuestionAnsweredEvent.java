package event.events;

import model.domain.AppUser;
import model.domain.ID;
import model.domain.QuestionAnswer;
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