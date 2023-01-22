package language.contentandrepository.criteria.answerandevaluation;

import language.contentandrepository.criteria.DomainType;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.user.Student;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionAnswerCriteria(
        @DomainType(QuestionAnswer.class)
        List<String> ids,
        @DomainType(Question.class)
        List<String> questions,
        @DomainType(Student.class)
        List<String> students,
        Boolean evaluated,
        LocalDateTime createdAfter,
        LocalDateTime createdBefore
 ) {

  @Builder public QuestionAnswerCriteria {}

}
