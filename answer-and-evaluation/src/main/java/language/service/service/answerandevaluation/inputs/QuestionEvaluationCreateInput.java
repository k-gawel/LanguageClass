package language.service.service.answerandevaluation.inputs;

import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.user.Teacher;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;

public record QuestionEvaluationCreateInput(
        @NotNull QuestionAnswer questionAnswer,
        @NotNull Teacher author,
        List<String> comments,
        @Nullable Integer score
) {}

