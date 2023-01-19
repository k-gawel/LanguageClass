package language.service.service.answerandevaluation.inputs;

import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.user.Student;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public record QuestionAnswerCreateInput(
        @NotNull(message = "quesstion.not_found") Question question,
        @NotNull(message = "author.not_found") Student author,
        @NotEmpty(message = "answers.empty") List<String> answers
) {
}
