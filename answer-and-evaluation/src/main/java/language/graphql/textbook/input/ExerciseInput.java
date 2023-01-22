package language.graphql.textbook.input;

import language.contentandrepository.model.domain.question.QuestionType;
import language.graphql.shared.Input;

public record ExerciseInput(
        String id,
        String author,
        String title,
        QuestionType type
) implements Input {
}
