package language.contentandrepository.criteria.answerandevaluation;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ExerciseEvaluationCriteria(
        List<String> ids,
        List<String> teachers,
        List<String> students,
        List<String> answers,
        List<String> exercises,
        LocalDateTime createdAfter,
        LocalDateTime createdBefore
) {

    @Builder public ExerciseEvaluationCriteria {}
    
}
