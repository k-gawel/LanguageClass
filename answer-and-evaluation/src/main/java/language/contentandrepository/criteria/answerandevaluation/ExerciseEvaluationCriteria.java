package language.contentandrepository.criteria.answerandevaluation;

import language.contentandrepository.criteria.DomainType;
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
        @DomainType(ExerciseEvaluation.class)
        List<String> ids,
        @DomainType(Teacher.class)
        List<String> teachers,
        @DomainType(Student.class)
        List<String> students,
        @DomainType(ExerciseAnswer.class)
        List<String> answers,
        @DomainType(Exercise.class)
        List<String> exercises,
        LocalDateTime createdAfter,
        LocalDateTime createdBefore
) {

    @Builder public ExerciseEvaluationCriteria {}
    
}
