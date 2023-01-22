package language.graphql.textbook.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import language.contentandrepository.criteria.textbook.ChapterCriteria;
import language.contentandrepository.criteria.textbook.ExampleCriteria;
import language.contentandrepository.criteria.textbook.ExerciseCriteria;
import language.contentandrepository.criteria.textbook.TextbookCriteria;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Example;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.repository.textbook.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TextbookElementsQuery implements GraphQLQueryResolver {

    private final TextbookElementsRepository textbookElementsRepository;
    private final TextbookRepository textbookRepository;
    private final ChapterRepository chapterRepository;
    private final ExampleRepository exampleRepository;
    private final ExerciseRepository exerciseRepository;

    @PreAuthorize("hasAnyAuthority('TEACHER', 'STUDENT')")
    @PostAuthorize("@textbookAccessFilter.hasAccess(authentication, returnObject)")
    public Textbook getTextbook(String id) {
        return textbookRepository.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('TEACHER', 'STUDENT')")
    @PostFilter("@textbookAccessFilter.hasAccess(authentication, filterObject)")
    public List<Textbook> findTextbook(TextbookCriteria criteria) {
        return textbookElementsRepository.find(criteria);
    }

    @PreAuthorize("isAuthenticated()")
    @PostAuthorize("@textbookAccessFilter.hasAccess(authentication, returnObject)")
    public Chapter getChapter(String id) {
        return chapterRepository.getById(id);
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostFilter("@textbookAccessFilter.hasAccess(authentication, filterObject)")
    public List<Chapter> findChapter(ChapterCriteria criteria) {
        return textbookElementsRepository.find(criteria);
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostAuthorize("@textbookAccessFilter.hasAccess(authentication, returnObject)")
    public Example getExample(String id) {
        return exampleRepository.getById(id);
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostFilter("@textbookAccessFilter.hasAccess(authentication, filterObject)")
    public List<Example> findExample(ExampleCriteria criteria) {
        return textbookElementsRepository.find(criteria);
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostAuthorize("@textbookAccessFilter.hasAccess(authentication, returnObject)")
    public Exercise getExercise(String id) {
        return exerciseRepository.getById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostFilter("@textbookAccessFilter.hasAccess(authentication, filterObject)")
    public List<Exercise> findExercise(ExerciseCriteria criteria) {
        return textbookElementsRepository.find(criteria);
    }

}
