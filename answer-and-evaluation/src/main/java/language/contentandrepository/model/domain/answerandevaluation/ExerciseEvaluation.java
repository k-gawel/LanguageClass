package language.contentandrepository.model.domain.answerandevaluation;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.Teacher;

import java.time.LocalDateTime;
import java.util.List;

public record ExerciseEvaluation(
        DomainID<ExerciseEvaluation> id,
        DomainID<Teacher> author,
        DomainID<ExerciseAnswer> answer,
        List<DomainID<QuestionEvaluation>> questionEvaluations,
        String comment,
        int score,
        LocalDateTime createdAt
)
implements Domain {
}
