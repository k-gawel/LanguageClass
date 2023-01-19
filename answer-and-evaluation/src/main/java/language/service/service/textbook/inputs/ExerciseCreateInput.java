package language.service.service.textbook.inputs;

import language.contentandrepository.model.domain.question.QuestionType;
import language.contentandrepository.model.domain.user.Teacher;

public record ExerciseCreateInput(
        Teacher author,
        String title,
        QuestionType type
) {
}
