package model.input;

import model.domain.ID;
import model.domain.answer.ExerciseAnswer;
import model.domain.evaluation.QuestionEvaluation;
import model.domain.user.Teacher;

import java.util.List;

public record ExerciseEvaluationInput(
        ID<ExerciseAnswer> answer,
        ID<Teacher> author,
        List<ID<QuestionEvaluation>> questionEvaluations,
        String comment,
        int rating
) {
}
