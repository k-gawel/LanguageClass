package language.graphql.mutation.textbook;

import language.graphql.textbook.input.TextbookAccessInput;
import language.graphql.textbook.mutation.TextbookMutation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TextbookMutationTest_changeAccess {

    @Autowired
    private TextbookMutation mutation;

    private final TextbookAccessInput input = new TextbookAccessInput(
            "teacher_1",
            "student_1",
            "texbook_1",
            true
    );

    @Test
    @WithMockUser(username = "student_1", authorities = "STUDENT")
    void changeAccess_asStudent() {
        assertThrows(AccessDeniedException.class, this::changeAccess);
    }

    @Test
    @WithMockUser(username = "teacher_2", authorities = "TEACHER")
    public void changeAccess_asWrongTeacher() {
        assertThrows(AccessDeniedException.class, this::changeAccess);
    }

    @Test
    @WithMockUser(username = "teacher_1", authorities = "TEACHER")
    public void changeAccess_asRightTeacher() {
        assertDoesNotThrow(this::changeAccess);
    }

    private void changeAccess() {
        mutation.changeAccess(input);
    }

}