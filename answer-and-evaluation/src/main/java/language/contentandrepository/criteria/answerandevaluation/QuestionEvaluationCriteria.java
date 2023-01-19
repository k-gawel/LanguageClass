package language.contentandrepository.criteria.answerandevaluation;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionEvaluationCriteria(
        List<String> ids,
        List<String> answers,
        List<String> questions,
        List<String> teachers,
        List<String> students,
        LocalDateTime after,
        LocalDateTime before
) {

    @Builder public QuestionEvaluationCriteria {}

}
