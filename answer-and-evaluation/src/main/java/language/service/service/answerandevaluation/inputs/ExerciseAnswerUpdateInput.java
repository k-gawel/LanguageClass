package language.service.service.answerandevaluation.inputs;

import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;

import java.util.List;

public record ExerciseAnswerUpdateInput(
        ExerciseAnswer exerciseAnswer,
        List<QuestionAnswer> questionAnswers
) {
}
