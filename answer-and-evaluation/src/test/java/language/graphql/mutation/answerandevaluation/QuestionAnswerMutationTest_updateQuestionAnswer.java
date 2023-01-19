package language.graphql.mutation.answerandevaluation;

import language.LanguageClassApp;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.repository.impl.answerandevaluation.BaseQuestionAnswerRepository;
import language.graphql.answerandevaluation.input.QuestionAnswerInput;
import language.graphql.answerandevaluation.mutation.QuestionAnswerMutation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = LanguageClassApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(MockitoJUnitRunner.class)
public class QuestionAnswerMutationTest_updateQuestionAnswer {

    @Autowired
    QuestionAnswerMutation questionAnswerMutation;
    @MockBean
    BaseQuestionAnswerRepository questionAnswerRepository;

    private final QuestionAnswer questionAnswer = new QuestionAnswer(
            new DomainID<>(QuestionAnswer.class, "question_answer_1"),
            new DomainID<>(Question.class, "question_1"),
            new DomainID<>(Student.class, "student_1"),
            Collections.emptyList(),
            LocalDateTime.now()
    );


    private final QuestionAnswerInput input = new QuestionAnswerInput(
            "question_answer_1",
            null,
            null,
            null
    );


    @Test
    @WithMockUser(username = "student_1", authorities = "STUDENT")
    public void test() {
        Mockito.when(questionAnswerRepository.getById("question_answer_1"))
                .thenReturn(questionAnswer);

        assertDoesNotThrow(() -> questionAnswerMutation.updateQuestionAnswer(input));
    }

    @Test
    @WithMockUser(username = "student_2", authorities = "STUDENT")
    public void throwWhenUserIsntAuthor() {
        Mockito.when(questionAnswerRepository.getById("question_answer_1"))
                .thenReturn(questionAnswer);

        assertThrows(Exception.class, () -> questionAnswerMutation.updateQuestionAnswer(input));
    }




}