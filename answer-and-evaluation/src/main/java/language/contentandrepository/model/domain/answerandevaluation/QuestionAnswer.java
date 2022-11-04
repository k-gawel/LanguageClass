package language.contentandrepository.model.domain.answerandevaluation;

import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.Student;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionAnswer(
        DomainID<QuestionAnswer> id,
        DomainID<Question> question,
        DomainID<Student> student,
        List<String> answers,
        LocalDateTime createdAt
) implements Domain {
}
