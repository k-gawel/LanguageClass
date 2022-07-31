package model.domain.answer;

import model.domain.Domain;
import model.domain.content.Exercise;
import model.domain.ID;
import model.domain.user.Student;

import java.sql.Timestamp;
import java.util.List;

public record ExerciseAnswer(
        ID<ExerciseAnswer> id,
        ID<Exercise> exercise,
        ID<Student> author,
        List<ID<QuestionAnswer>> answers,
        Timestamp createdAt
) implements Domain {
}
