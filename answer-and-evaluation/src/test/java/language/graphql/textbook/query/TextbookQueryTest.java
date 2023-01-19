package language.graphql.textbook.query;

import language.LanguageClassApp;
import language.contentandrepository.criteria.textbook.TextbookCriteria;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.persistence.entity.textbook.TextbookAccess;
import language.contentandrepository.repository.textbook.TextbookAccessRepository;
import language.contentandrepository.repository.textbook.TextbookElementsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import utils.testmodels.TestTextbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LanguageClassApp.class)
public class TextbookQueryTest {

    @MockBean
    TextbookElementsRepository textbookElementsRepository;

    @MockBean
    TextbookAccessRepository accessRepository;

    @Autowired
    private TextbookQuery textbookQuery;

    Student user1 = new Student(new DomainID<>(Student.class, "user1"), "student1");
    Student user2 = new Student(new DomainID<>(Student.class, "user2"), "student2");
    Teacher teacher = new Teacher(new DomainID<>(Teacher.class, "teacher"), "teacher");

    List<Textbook> textbooks = new ArrayList<>(IntStream.range(0, 5).mapToObj(i -> TestTextbook.textbookEmptyContent()).collect(Collectors.toList()));
    List<Textbook> textbooksUser1 = IntStream.of(0, 1, 2, 3).mapToObj(textbooks::get).collect(Collectors.toList());
    List<Textbook> textbooksUser2 = IntStream.of(0, 1, 4).mapToObj(textbooks::get).collect(Collectors.toList());
    List<Textbook> textbooksTeacher = new ArrayList<>(textbooks);

    List<TextbookAccess> textbookAccessList = Stream.concat(
            Stream.concat(
            IntStream.range(0,2).mapToObj(textbooks::get).map(t -> new TextbookAccess(t.id(), true, new HashSet<>())),
            IntStream.range(2, 4).mapToObj(textbooks::get).map(t -> new TextbookAccess(t.id(), false, new HashSet<>(Collections.singleton(user1.id()))))
            ),
            IntStream.range(4,5).mapToObj(textbooks::get).map(t -> new TextbookAccess(t.id(), false, new HashSet<>(Collections.singleton(user2.id()))))
            ).toList();

    @BeforeEach
    public void mock() {
        Mockito.when(textbookElementsRepository.find((TextbookCriteria) ArgumentMatchers.any())).thenAnswer(t -> {
            System.out.println("Returning this");
            return textbooks;
        });
        Mockito.when(accessRepository.getByTextbook(ArgumentMatchers.any(DomainID.class))).then(t -> textbookAccessList.stream().filter(a -> a.id().equals(t.getArgument(0))).findFirst().orElseThrow());
    }

    @Test
    @WithMockUser(username = "user1", authorities = "STUDENT")
    public void student1Test() {
        assertEquals(textbooksUser1, textbookQuery.findTextbook(null));
    }

    @Test
    @WithMockUser(username = "user2", authorities = "STUDENT")
    public void student2Test() {
        assertEquals(textbooksUser2, textbookQuery.findTextbook(null));
    }

    @Test
    @WithMockUser(authorities = "TEACHER")
    public void teacherTest() {
        assertEquals(textbooksTeacher, textbookQuery.findTextbook(null));
    }

}