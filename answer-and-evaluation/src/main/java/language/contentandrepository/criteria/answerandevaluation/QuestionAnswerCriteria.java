package language.contentandrepository.criteria.answerandevaluation;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.user.Student;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionAnswerCriteria(
        List<DomainID<QuestionAnswer>> ids,
        List<DomainID<Question>> questions,
        List<DomainID<Student>> students,
        Boolean evaluated,
        LocalDateTime createdAfter,
        LocalDateTime createdBefore
 ) {

  @Builder public QuestionAnswerCriteria {}

}
