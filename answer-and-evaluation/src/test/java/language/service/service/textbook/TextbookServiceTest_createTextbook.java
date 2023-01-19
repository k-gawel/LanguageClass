package language.service.service.textbook;

import utils.dummyrepositories.TestTeacherRepositoryFactory;
import utils.dummyrepositories.TestTextbookRepositoryFactory;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.repository.textbook.TextbookRepository;
import language.contentandrepository.repository.user.TeacherRepository;
import language.service.service.textbook.implementations.PublisherTextbookService;
import language.service.service.textbook.inputs.TextbookCreateInput;
import org.junit.jupiter.api.Test;
import utils.testmodels.TestClock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TextbookServiceTest_createTextbook {

    private final TextbookRepository textbookRepository = TestTextbookRepositoryFactory.emptyRepository();
    private final Teacher teacher = new Teacher(new DomainID<>(Teacher.class, "teacher_1"), "name");
    private final TeacherRepository teacherRepository = TestTeacherRepositoryFactory.repository(
            List.of(teacher)
    );
    private final PublisherTextbookService textbookService = new PublisherTextbookService(
            TestClock.INSTANCE(),
            textbookRepository
    );

    private String existingTeacherId = "teacher_1";

    @Test
    public void textbookIsAccessibleFromRepositoryAfterCreation() {
        var textbook = textbookService.createTextbook(input1());
        var textbookFromRepository = textbookRepository.findById(textbook.id());

        assertTrue(textbookFromRepository.isPresent());
        assertEquals(textbook, textbookFromRepository.get());
    }

    @Test
    public void createDifferentIdWhenInputIsTheSame() {
        var textbook1 = textbookService.createTextbook(input1());
        var textbook2 = textbookService.createTextbook(input1());
        assertNotEquals(textbook1.id(), textbook2.id());
    }

    private TextbookCreateInput input1() {
        return new TextbookCreateInput(
                teacher,
                "textbook_name_1"
        );
    }


}