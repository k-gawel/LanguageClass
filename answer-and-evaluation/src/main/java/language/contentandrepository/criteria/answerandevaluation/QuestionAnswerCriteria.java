package language.contentandrepository.criteria.answerandevaluation;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionAnswerCriteria(
        List<String> ids,
        List<String> questions,
        List<String> students,
        boolean evaluated,
        LocalDateTime createdAfter,
        LocalDateTime createdBefore
 ) {

}