package filter;

import model.domain.user.AppUser;
import model.domain.ID;
import model.domain.answer.QuestionAnswer;
import model.domain.user.Teacher;
import org.springframework.stereotype.Component;

@Component
public class QuestionAnswerPredicate implements ModelPredicate<QuestionAnswer> {

    @Override
    public boolean hasAccess(ID<? extends AppUser> user, QuestionAnswer answer) {
        return user.type().equals(Teacher.class) || answer.student().equals(user);
    }

}
