package language.service.service.answerandevaluation.inputs;

import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;

import java.util.List;

public record ExerciseAnswerCreateInput(
        Student author,
        Exercise exercise,
        List<QuestionAnswer> questionAnswers
) {
}
