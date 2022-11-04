package language.contentandrepository.model.domain.answerandevaluation;

import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.Student;

import java.time.LocalDateTime;
import java.util.List;

public record ExerciseAnswer(
        DomainID<ExerciseAnswer> id,
        DomainID<Exercise> exercise,
        DomainID<Student> author,
        List<DomainID<QuestionAnswer>> answers,
        LocalDateTime createdAt
) implements Domain {
}
