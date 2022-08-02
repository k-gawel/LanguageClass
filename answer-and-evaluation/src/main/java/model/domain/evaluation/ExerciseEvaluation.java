package model.domain.evaluation;

import model.domain.Domain;
import model.domain.ID;
import model.domain.answer.ExerciseAnswer;
import model.domain.user.Teacher;

import java.sql.Timestamp;
import java.util.List;

public record ExerciseEvaluation(
        ID<ExerciseEvaluation> id,
        ID<Teacher> author,
        ID<ExerciseAnswer> answer,
        List<ID<QuestionEvaluation>> questionEvaluations,
        String comment,
        int score,
        Timestamp createdAt
)
implements Domain {
}
