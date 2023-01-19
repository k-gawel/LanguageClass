package language.contentandrepository.criteria.answerandevaluation;

import java.time.LocalDateTime;
import java.util.List;

public record ExerciseAnswerCriteria(
        List<String> ids,
        List<String> exercises,
        List<String> students,
        LocalDateTime createdAfter,
        LocalDateTime createdBefore,
        boolean evaluated
) {
}
