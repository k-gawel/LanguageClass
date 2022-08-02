package model.input;

import model.domain.ID;
import model.domain.answer.ExerciseAnswer;
import model.domain.content.Exercise;
import model.domain.evaluation.QuestionEvaluation;
import model.domain.user.Teacher;

import java.util.List;

public record ExerciseEvaluationInput(
        ID<ExerciseAnswer> answer,
        ID<Teacher> author,
        String comment,
        int rating
) {
}
