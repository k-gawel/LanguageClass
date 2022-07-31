package model.domain.content;

import model.domain.Domain;
import model.domain.ID;

public record Exercise(
        ID<Exercise> id,
        QuestionType questionType
) implements Domain {
}
