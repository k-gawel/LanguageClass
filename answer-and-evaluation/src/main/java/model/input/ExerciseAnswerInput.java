package model.input;

import model.domain.ID;
import model.domain.answer.QuestionAnswer;
import model.domain.content.Exercise;
import model.domain.user.Student;

import java.util.List;

public record ExerciseAnswerInput(
        ID<Exercise> exercise,
        ID<Student> author,
        List<ID<QuestionAnswer>> questionAnswers
) {
}
