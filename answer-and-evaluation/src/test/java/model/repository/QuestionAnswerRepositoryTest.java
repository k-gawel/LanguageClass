package model.repository;

import model.domain.content.ChooseAWordQuestion;
import model.domain.ID;
import model.domain.answer.QuestionAnswer;
import model.domain.user.Student;
import model.repository.utils.Converter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionAnswerRepositoryTest extends AbstractRepositoryTest{

    private QuestionAnswerRepository repository;

    @BeforeEach
    public void init() throws SQLException, ClassNotFoundException {
        super.init();
        this.repository = new QuestionAnswerRepository(new NamedParameterJdbcTemplate(dataSource), null);
    }

    @Test
    public void whenCorrectId_thenReturn() {

        var id = new ID<>(QuestionAnswer.class, "answer_id");

        var result = repository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(new ID<>(Student.class, "student_id"), result.get().student());
        assertEquals(new ID<>(ChooseAWordQuestion.class, "question_id"), result.get().question());
        assertEquals(List.of("answer1", "answer2"), result.get().answers());
    }

    @Override
    public void createData(DataSource dataSource) throws SQLException {
        dataSource.getConnection().createStatement()
                .executeUpdate(
                            "DELETE FROM textbook_chapter;" +
                                    "DELETE FROM textbook;" +
                                    "DELETE FROM question_answer;" +
                                    "DELETE FROM app_user; " +
                                    "DELETE FROM chooseaword_content; " +
                                    "DELETE FROM fillaword_content"
                );

        var answers = Converter.ToDatabase.stringList(List.of("answer1", "answer2"));

        dataSource.getConnection().createStatement().executeUpdate(
                "INSERT INTO app_user (key, id, type) VALUES (1, 'student_id', 'STUDENT');" +
                    "INSERT INTO chooseaword_content (key, id) VALUES (1, 'question_id');" +
                    "INSERT INTO question_answer (id, student, question, answers) VALUES  ('answer_id', 1, 1, '" + answers + "');"
        );
    }

    @AfterEach
    public void clean() throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(
                "DELETE FROM question_answer;" +
                    "DELETE FROM chooseaword_content;" +
                    "DELETE FROM app_user;"
        );
    }

}