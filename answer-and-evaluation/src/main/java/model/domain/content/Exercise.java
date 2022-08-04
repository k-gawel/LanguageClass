package model.domain.content;

import model.domain.Domain;
import model.domain.ID;

import java.util.List;

public record Exercise(
        ID<Exercise> id,
        QuestionType questionType,
        List<ID<? extends Question>> questions
) implements Domain {
}
