package language.contentandrepository.model.domain.answerandevaluation;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.Teacher;

public record QuestionEvaluation(
        DomainID<QuestionEvaluation> id,
        DomainID<QuestionAnswer> answer,
        DomainID<Teacher> author,
        String comments,
        int score,
        java.time.LocalDateTime createdAt
) implements Domain {
}
