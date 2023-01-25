package language.contentandrepository.criteria.answerandevaluation;

import language.contentandrepository.criteria.DomainCriteria;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseEvaluation;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ExerciseEvaluationCriteria(
        List<DomainID<ExerciseEvaluation>> ids,
        List<DomainID<Teacher>> teachers,
        List<DomainID<Student>> students,
        List<DomainID<ExerciseAnswer>> answers,
        List<DomainID<Exercise>> exercises,
        LocalDateTime createdAfter,
        LocalDateTime createdBefore
) implements DomainCriteria<ExerciseEvaluation> {

    @Builder public ExerciseEvaluationCriteria {}
    
}
