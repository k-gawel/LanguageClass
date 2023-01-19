package language.contentandrepository.model.domain.answerandevaluation;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.Teacher;

public record QuestionEvaluation(
        DomainID<QuestionEvaluation> id,
        DomainID<QuestionAnswer> answer,
        DomainID<Teacher> author,
        java.util.List<String> comments,
        Integer score,
        java.time.LocalDateTime createdAt
) implements Domain {
}
