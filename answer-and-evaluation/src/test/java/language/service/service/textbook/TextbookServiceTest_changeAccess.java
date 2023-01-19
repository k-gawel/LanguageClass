package language.service.service.textbook;

import utils.dummyrepositories.TestTextbookRepositoryFactory;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.persistence.entity.textbook.TextbookAccess;
import language.service.service.textbook.implementations.PublisherTextbookService;
import language.service.service.textbook.inputs.TextbookModifyAccessInput;
import language.service.service.textbook.services.TextbookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.testmodels.TestClock;
import utils.testmodels.TestStudent;
import utils.testmodels.TestTeacher;
import utils.testmodels.TestTextbook;

import java.util.Collections;
import java.util.HashSet;

public class TextbookServiceTest_changeAccess {

    private final Textbook textbook = TestTextbook.textbookEmptyContent();
    private final Student student1 = TestStudent.generate();
    private final Student student2 = TestStudent.generate();

    private final TextbookAccess textbookAccess = new TextbookAccess(
            textbook.id(), false, new HashSet<>(Collections.singleton(student1.id()))
    );

    private final TextbookService textbookService = new PublisherTextbookService(
            TestClock.INSTANCE(),
            TestTextbookRepositoryFactory.repository(Collections.singletonList(TestTextbook.textbookWithContent()))
    );


    @Test
    public void falseWhenStudentNotGrantedAndTryingToRemove() {
        Assertions.assertFalse(textbookAccess.allowedStudents().contains(student2.id()));
        var input = new TextbookModifyAccessInput(
                student2,
                textbook,
                TestTeacher.generate(),
                textbookAccess,
                false
        );
        Assertions.assertFalse(textbookService.changeAccess(input));
        Assertions.assertFalse(textbookAccess.allowedStudents().contains(student2.id()));
    }


    @Test
    public void falseWhenStudentIsGrantedAndTryingToGrant() {
        Assertions.assertTrue(textbookAccess.allowedStudents().contains(student1.id()));
        var input = new TextbookModifyAccessInput(
                student1,
                textbook,
                TestTeacher.generate(),
                textbookAccess,
                true
        );
        Assertions.assertFalse(textbookService.changeAccess(input));
        Assertions.assertTrue(textbookAccess.allowedStudents().contains(student1.id()));
    }

    @Test
    public void trueWhenStudentIsNotGrantedAndTryingToGrant() {
        Assertions.assertFalse(textbookAccess.allowedStudents().contains(student2.id()));
        var input = new TextbookModifyAccessInput(
                student2,
                textbook,
                TestTeacher.generate(),
                textbookAccess,
                true
        );
        Assertions.assertTrue(textbookService.changeAccess(input));
        Assertions.assertTrue(textbookAccess.allowedStudents().contains(student2.id()));
    }

    @Test
    public void trueWhenStudentIsGrantedAndTryingToRemove() {
        Assertions.assertTrue(textbookAccess.allowedStudents().contains(student1.id()));
        var input = new TextbookModifyAccessInput(
                student1,
                textbook,
                TestTeacher.generate(),
                textbookAccess,
                false
        );
        Assertions.assertTrue(textbookService.changeAccess(input));
        Assertions.assertFalse(textbookAccess.allowedStudents().contains(student1.id()));
    }




}