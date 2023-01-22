package language.contentandrepository.criteria.textbook;

import language.contentandrepository.criteria.DomainType;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.question.QuestionType;
import language.contentandrepository.model.domain.textbook.Exercise;
import lombok.Builder;

import java.util.List;

public record ExerciseCriteria(
        @DomainType(Exercise.class)
        List<String> ids,
        String title,
        List<QuestionType> questionTypes,
        @DomainType(Question.class)
        List<String> containsQuestion
) {

    @Builder public ExerciseCriteria {}

}
