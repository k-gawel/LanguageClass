package language.service.service.answerandevaluation.inputs;

import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.model.domain.user.Teacher;

import java.util.List;

public record ExerciseEvaluationCreateInput(
        ExerciseAnswer exerciseAnswer,
        Teacher author,
        List<QuestionEvaluation> questionEvaluations,
        int rating,
        String comment
        ) {
}
