package language.contentandrepository.criteria.textbook;

import language.contentandrepository.criteria.DomainCriteria;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.question.QuestionType;
import language.contentandrepository.model.domain.textbook.Exercise;
import lombok.Builder;

import java.util.List;

public record ExerciseCriteria(
        List<DomainID<Exercise>> ids,
        String title,
        List<QuestionType> questionTypes,
        List<DomainID<Question>> containsQuestion
) implements DomainCriteria<Exercise> {

    @Builder public ExerciseCriteria {}

}
