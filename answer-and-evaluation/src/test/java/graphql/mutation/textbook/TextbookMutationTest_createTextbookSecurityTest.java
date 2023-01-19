package graphql.mutation.textbook;

import language.LanguageClassApp;
import language.graphql.textbook.mutation.TextbookMutation;
import language.graphql.textbook.input.TextbookInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = LanguageClassApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TextbookMutationTest_createTextbookSecurityTest {

    @Autowired
    TextbookMutation textbookMutation;

    private final TextbookInput input = new TextbookInput(
            "teacher_1",
            "test containerId title"
    );

    @Test
    @WithMockUser(username = "teacher_1", authorities = "TEACHER")
    public void createTextbookAsTeacher() {
        assertDoesNotThrow(this::createTextbook);
    }

    @Test
    @WithMockUser(username = "teacher_2", authorities = "TEACHER")
    public void createTextbookAsWrongTeacher() {
        assertThrows(AccessDeniedException.class, this::createTextbook);
    }

    @Test
    @WithMockUser(username = "student_1", authorities = "STUDENT")
    public void createTextbookAsStudent() {
        assertThrows(Exception.class, this::createTextbook);
    }

    private void createTextbook() {
        textbookMutation.createTextbook(input);
    }

}