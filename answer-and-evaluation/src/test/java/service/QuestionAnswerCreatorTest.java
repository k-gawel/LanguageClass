package service;

import model.domain.ID;
import model.domain.answer.QuestionAnswer;
import model.domain.content.ChooseAWordQuestion;
import model.domain.content.Question;
import model.domain.user.Student;
import model.repository.Provider;
import model.repository.QuestionAnswerRepository;
import model.repository.QuestionRepository;
import model.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionAnswerCreatorTest {

    private final QuestionAnswerRepository questionAnswerRepository;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DataSource dataSource;
    private final QuestionAnswerCreator creator;

    QuestionAnswerCreatorTest() {
        this.dataSource = Provider.getDataSource();
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        var questionRepository = new QuestionRepository(jdbcTemplate);
        questionAnswerRepository = new QuestionAnswerRepository(jdbcTemplate, questionRepository);
        creator = new QuestionAnswerCreator(jdbcTemplate, new UserRepository(jdbcTemplate), questionRepository);
    }

    @Test
    public void saveTest() {
        var id = new ID<>(QuestionAnswer.class, "questionAnswer");
        assertTrue(questionAnswerRepository.findById(id).isEmpty());

        var answer = new QuestionAnswer(
                id,
                new ID<>(ChooseAWordQuestion.class, "question"),
                new ID<>(Student.class, "student"),
                List.of("answer1", "answer2", "answer3"),
                new Timestamp(new Date().getTime())
        );

        creator.save(answer);

        assertEquals(answer, questionAnswerRepository.findById(id).get());
    }

    @BeforeEach
    public void createData() {
        try {
            dataSource.getConnection().createStatement().executeUpdate(
                    """
                        INSERT INTO app_user (key, id, name, type)
                                    VALUES (1, 'student', 'student', 'STUDENT'),
                                           (2, 'teacher', 'teacher', 'TEACHER');
                        INSERT INTO chooseaword_content (key, id, content_parts, correct_answers, word_choice)
                                                 VALUES (1, 'question', '', '', '')
                        """
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void clearData() {
        try {
            dataSource.getConnection().createStatement()
                    .executeUpdate("DELETE FROM question_answer;" +
                                       "DELETE FROM chooseaword_content; " +
                                       "DELETE FROM app_user;" +
                            "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}