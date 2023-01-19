package language.graphql.textbook.filter;

import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.model.domain.user.AppUser;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.persistence.entity.textbook.TextbookAccess;
import language.contentandrepository.repository.impl.textbook.TextbookElementsRepositoryImpl;
import language.contentandrepository.repository.textbook.TextbookAccessRepository;
import language.contentandrepository.repository.textbook.TextbookElementsRepository;
import language.contentandrepository.repository.textbook.TextbookRepository;
import language.contentandrepository.repository.user.AppUserRepository;
import org.junit.jupiter.api.Test;
import utils.dummyrepositories.TestAppUserRepositoryFactory;
import utils.dummyrepositories.TestTextbookAccessRepositoryFactory;
import utils.dummyrepositories.TestTextbookRepositoryFactory;
import utils.testmodels.TestChapter;
import utils.testmodels.TestStudent;
import utils.testmodels.TestTeacher;
import utils.testmodels.TestTextbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextbookElementsFilterTest {
    Student student1 = TestStudent.generate();
    Student student2 = TestStudent.generate();
    Teacher teacher = TestTeacher.generate();
    AppUserRepository appUserRepository = TestAppUserRepositoryFactory.repository(List.of(teacher, student2, student1));

    Textbook textbookPublic = TestTextbook.textbookEmptyContent();
    Textbook textbookStudent1 = TestTextbook.textbookEmptyContent();
    Textbook textbookStudent2 = TestTextbook.textbookEmptyContent();
    List<Textbook> allTextbooks = new ArrayList<>(List.of(textbookPublic, textbookStudent1, textbookStudent2));
    TextbookRepository textbookRepository = TestTextbookRepositoryFactory.repository(allTextbooks);

    TextbookAccess accessTextbookPublic = new TextbookAccess(textbookPublic.id(), true, Collections.emptySet());
    TextbookAccess accessTextbookStudent1 = new TextbookAccess(textbookStudent1.id(), false, Set.of(student1.id()));
    TextbookAccess accessTextbookStudent2 = new TextbookAccess(textbookStudent2.id(), false, Set.of(student2.id()));
    List<TextbookAccess> textbookAccesses = List.of(accessTextbookPublic, accessTextbookStudent1, accessTextbookStudent2);
    TextbookAccessRepository textbookAccessRepository = TestTextbookAccessRepositoryFactory.repository(textbookAccesses);

    TextbookElementsRepository textbookElementsRepository = new TextbookElementsRepositoryImpl(
            textbookRepository,
            textbookAccessRepository,
            null,
            null,
            null
    );

    final TextbookElementsFilter filter = new TextbookElementsFilter(
            appUserRepository,
            textbookAccessRepository,
            textbookElementsRepository
    );


    @Test
    public void textbookFilterTest() {

        assertTrue(filter.hasAccess(teacher, textbookPublic) && filter.hasAccess(teacher, textbookStudent1) && filter.hasAccess(teacher, textbookStudent2));
        assertTrue(filter.hasAccess(student1, textbookPublic) && filter.hasAccess(student1, textbookStudent1));
        assertTrue(filter.hasAccess(student2, textbookPublic) && filter.hasAccess(student2, textbookStudent2));
        assertFalse(filter.hasAccess(student1, textbookStudent2) && filter.hasAccess(student2, textbookStudent1));
    }

    @Test
    public void chapterFilterAllowedTest() {
        var chapterPublic = TestChapter.emptyChapter();
        textbookPublic.chapters().add(chapterPublic.id());

        var chapterStudent1 = TestChapter.emptyChapter();
        textbookStudent1.chapters().add(chapterStudent1.id());

        var chapterStudent2 = TestChapter.emptyChapter();
        textbookStudent2.chapters().add(chapterStudent2.id());

        assertHaveAccess(chapterPublic, teacher, student1, student2);
        assertHaveAccess(chapterStudent1, teacher, student1);
        assertHaveAccess(chapterStudent2, teacher, student2);
        assertHaveNoAccess(chapterStudent1, student2);
        assertHaveNoAccess(chapterStudent2, student1);
    }

    private void assertHaveAccess(Chapter chapter, AppUser... users) {
        for (AppUser appUser : users) {
            assertTrue(filter.hasAccess(appUser, chapter));
        }
    }

    private void assertHaveNoAccess(Chapter chapter, AppUser... users) {
        for (AppUser user : users) {
            assertFalse(filter.hasAccess(user, chapter));
        }
    }


}