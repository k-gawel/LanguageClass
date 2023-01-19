package language.service.service.answerandevaluation.inputs;

import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;

import javax.validation.constraints.NotNull;
import java.util.List;

public record QuestionEvaluationUpdateInput(
        @NotNull(message = "question_evaluation.not_found") QuestionEvaluation questionEvaluation,
        List<String> comments,
        Integer score
) {
}
