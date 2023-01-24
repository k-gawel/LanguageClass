package language.contentandrepository.criteria.answerandevaluation;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.model.domain.user.Student;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record ExerciseAnswerCriteria(
        List<String> ids,
        List<DomainID<Exercise>> exercises,
        List<DomainID<Student>> students,
        LocalDateTime createdAfter,
        LocalDateTime createdBefore,
        boolean evaluated
) {

    @Builder public ExerciseAnswerCriteria {}

}
