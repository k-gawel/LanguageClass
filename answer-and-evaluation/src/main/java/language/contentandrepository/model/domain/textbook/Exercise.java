package language.contentandrepository.model.domain.textbook;

import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.question.QuestionType;

import java.util.List;

public record Exercise(
        DomainID<Exercise> id,
        String title,
        QuestionType questionType,
        List<DomainID<Question>> questions
) implements ChapterContent {
}
