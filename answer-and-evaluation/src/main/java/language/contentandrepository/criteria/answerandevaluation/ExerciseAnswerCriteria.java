package language.contentandrepository.criteria.answerandevaluation;

import language.contentandrepository.criteria.DomainCriteria;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.model.domain.user.Student;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record ExerciseAnswerCriteria(
        List<DomainID<ExerciseAnswer>> ids,
        List<DomainID<Exercise>> exercises,
        List<DomainID<Student>> students,
        LocalDateTime createdAfter,
        LocalDateTime createdBefore,
        boolean evaluated
) implements DomainCriteria<ExerciseAnswer> {

    @Builder public ExerciseAnswerCriteria {}

}
