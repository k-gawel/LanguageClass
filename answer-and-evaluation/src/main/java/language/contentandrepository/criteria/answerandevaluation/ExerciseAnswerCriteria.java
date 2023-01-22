package language.contentandrepository.criteria.answerandevaluation;

import language.contentandrepository.criteria.DomainType;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.model.domain.user.Student;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record ExerciseAnswerCriteria(
        @DomainType(ExerciseAnswer.class)
        List<String> ids,
        @DomainType(Exercise.class)
        List<String> exercises,
        @DomainType(Student.class)
        List<String> students,
        LocalDateTime createdAfter,
        LocalDateTime createdBefore,
        boolean evaluated
) {

    @Builder public ExerciseAnswerCriteria {}

}
