package language.contentandrepository.repository.impl.textbook;

import language.contentandrepository.criteria.textbook.ChapterCriteria;
import language.contentandrepository.criteria.textbook.ExampleCriteria;
import language.contentandrepository.criteria.textbook.ExerciseCriteria;
import language.contentandrepository.criteria.textbook.TextbookCriteria;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Example;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.repository.textbook.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static language.contentandrepository.criteria.CriteriaUtils.*;

@Repository
@RequiredArgsConstructor
public class TextbookElementsRepositoryImpl implements TextbookElementsRepository {

    private final TextbookRepository textbookRepository;
    private final TextbookAccessRepository textbookAccessRepository;
    private final ChapterRepository chapterRepository;
    private final ExampleRepository exampleRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public List<Textbook> find(TextbookCriteria criteria) {

        Predicate<Textbook> predicate = textbook -> {
            Supplier<Collection<DomainID<Student>>> allowedUsersSupplier = () -> textbookAccessRepository.getByTextbook(textbook.id()).allowedStudents();
            return containsOrEmpty(criteria.ids(), textbook.id()) &&
                    containsOrEmpty(criteria.authors(), textbook.author()) &&
                    containsAllOrEmpty(textbook.chapters(), criteria.containedChapters()) &&
                    containsOrEmpty(textbook.title(), criteria.title()) &&
                    isBetween(criteria.after(), criteria.before(), textbook.createdAt()) &&
                    containsAllOrEmpty(allowedUsersSupplier, criteria.allowedUsers());
        };
        return textbookRepository.find(predicate);
    }

    @Override
    public List<Chapter> find(ChapterCriteria criteria) {

        Predicate<Chapter> predicate = chapter -> {
            var textbooksChapterSupplier =
                    textbookRepository.find(t -> criteria.textbooks().contains(t.id().id()))
                            .stream().map(Textbook::chapters)
                            .toList();

            return containsOrEmpty(criteria.ids(), chapter.id()) &&
                   allContainsOrEmpty(textbooksChapterSupplier, chapter.id().id()) &&
                   containsOrEmpty(chapter.title(), criteria.name()) &&
                   containsAllOrEmpty(chapter.content(), criteria.containingContent());
        };
        return chapterRepository.find(predicate);
    }

    @Override
    public List<Example> find(ExampleCriteria criteria) {
        Predicate<Example> predicate = example ->
                containsOrEmpty(criteria.ids(), example.id()) &&
                containsOrEmpty(example.title(), criteria.title());

        return exampleRepository.find(predicate);
    }

    @Override
    public List<Exercise> find(ExerciseCriteria criteria) {
        Predicate<Exercise> predicate = exercise ->
                containsOrEmpty(criteria.ids(), exercise.id()) &&
                containsOrEmpty(criteria.questionTypes(), exercise.questionType()) &&
                containsAllOrEmpty(exercise.questions(), criteria.containsQuestion()) &&
                containsOrEmpty(exercise.title(), criteria.title());

        return exerciseRepository.find(predicate);
    }
}
