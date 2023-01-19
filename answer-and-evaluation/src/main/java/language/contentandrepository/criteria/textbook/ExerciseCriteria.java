package language.contentandrepository.criteria.textbook;

import language.contentandrepository.model.domain.question.QuestionType;
import lombok.Builder;

import java.util.List;

public record ExerciseCriteria(
        List<String> ids,
        String title,
        List<QuestionType> questionTypes,
        List<String> containsQuestion
) {

    @Builder public ExerciseCriteria {}

}
