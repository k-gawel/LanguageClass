package language.contentandrepository.repository.textbook;

import language.contentandrepository.criteria.textbook.ChapterCriteria;
import language.contentandrepository.criteria.textbook.ExampleCriteria;
import language.contentandrepository.criteria.textbook.ExerciseCriteria;
import language.contentandrepository.criteria.textbook.TextbookCriteria;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Example;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.model.domain.textbook.Textbook;

import java.util.List;

public interface TextbookElementsRepository {

    List<Textbook> find(TextbookCriteria criteria);
    List<Chapter> find(ChapterCriteria criteria);
    List<Example> find(ExampleCriteria criteria);
    List<Exercise> find(ExerciseCriteria criteria);

}
