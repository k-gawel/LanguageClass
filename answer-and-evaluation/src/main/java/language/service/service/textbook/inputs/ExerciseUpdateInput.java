package language.service.service.textbook.inputs;

import language.contentandrepository.model.domain.question.QuestionType;
import language.contentandrepository.model.domain.textbook.Exercise;

public record ExerciseUpdateInput(
        Exercise exercise,
        String title,
        QuestionType questionType
) {
}
