package model.domain;

import java.util.List;

public record ExerciseAnswer(
        ID<ExerciseAnswer> id,
        ID<Exercise> exercise,
        ID<Student> author,
        List<ID<QuestionAnswer>> answers
) implements Domain{
}
