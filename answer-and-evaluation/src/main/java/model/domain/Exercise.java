package model.domain;

public record Exercise(
        ID<Exercise> id,
        QuestionType questionType
) implements Domain {
}
