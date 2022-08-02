package filter;

import model.domain.user.AppUser;
import model.domain.answer.ExerciseAnswer;
import model.domain.ID;
import model.domain.user.Teacher;
import org.springframework.stereotype.Component;

@Component
public class ExerciseAnswerPredicate implements ModelPredicate<ExerciseAnswer> {

    @Override
    public boolean hasAccess(ID<? extends AppUser> user, ExerciseAnswer answer) {
        return user.type().equals(Teacher.class) || answer.author().equals(user);
    }

}
