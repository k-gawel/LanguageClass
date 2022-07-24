package model.repository;

import model.domain.ChooseAWordQuestion;
import model.domain.ID;
import model.domain.QuestionAnswer;
import model.domain.Student;
import model.repository.utils.Converter;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionAnswerRepositoryTest {

    private final QuestionAnswerRepository repository;


    QuestionAnswerRepositoryTest() throws ClassNotFoundException, SQLException {
        var datasource = Provider.getDataSource();
        createData(datasource);
        this.repository = new QuestionAnswerRepository(datasource, null);
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

    private void createData(DataSource dataSource) throws SQLException {
        dataSource.getConnection().createStatement()
                .executeUpdate(
                        "DROP TABLE IF EXISTS chooseaword_content; " +
                            "DROP TABLE IF EXISTS app_user; " +
                            "DROP TABLE IF EXISTS question_answer;" +
                            "DROP TABLE IF EXISTS fillaword_content"
                );

        dataSource.getConnection().createStatement().executeUpdate(
                    "CREATE TABLE app_user (identifier bigint, id varchar(250)); " +
                        "CREATE TABLE fillaword_content (identifier bigint, id varchar(250)); " +
                        "CREATE TABLE chooseaword_content (identifier bigint, id varchar(250)); " +
                        "CREATE TABLE question_answer (id varchar(250), student bigint, question bigint, answers varchar(250)); "
        );

        var answers = Converter.ToDatabase.stringList(List.of("answer1", "answer2"));

        dataSource.getConnection().createStatement().executeUpdate(
                "INSERT INTO app_user VALUES (1, 'student_id');" +
                    "INSERT INTO chooseaword_content VALUES (1, 'question_id');" +
                    "INSERT INTO question_answer VALUES ('answer_id', 1, 1, '" + answers + "');"
        );
    }

}