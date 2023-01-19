package language.service.service.answerandevaluation.inputs;

import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public record QuestionAnswerUpdateInput(
        @NotNull(message = "question_answer.not_found") QuestionAnswer questionAnswer,
        @NotEmpty(message = "answers.not_found") List<String> answers
) {
}
