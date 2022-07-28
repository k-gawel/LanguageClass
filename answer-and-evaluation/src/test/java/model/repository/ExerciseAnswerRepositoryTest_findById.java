package model.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.UUID;

class ExerciseAnswerRepositoryTest_findById extends AbstractRepositoryTest {

    private  ExerciseAnswerRepository repository;

    @BeforeEach
    public void init() throws SQLException, ClassNotFoundException {
        super.init();
        this.repository = new ExerciseAnswerRepository(new NamedParameterJdbcTemplate(dataSource));
    }

    @AfterEach
    public void clean() throws SQLException {
        deleteData(dataSource);
    }


    @Test
    public void when_id_then_result() {
    }

    protected void createData(DataSource ds) throws SQLException {
        ds.getConnection().createStatement().executeUpdate(
                    "INSERT INTO app_user (key, id, type) VALUES (1, 'STUDENT_ID', 'STUDENT');" +
                        "INSERT INTO chooseaword_content (key, id) VALUES (1, 'QUESTION_ID_1'), (2, 'QUESTION_ID_2');" +
                        "INSERT INTO question_answer (key, id) VALUES (1, 'ANSWER_ID_1'), (2, 'ANSWER_ID_2');" +
                        "INSERT INTO exercise_content (key, id, title, question_type) VALUES (1, 'EXERCISE_ID', 'TITLE', 'choose_a_word');" +
                        "INSERT INTO exercise_answer (key, id, student, exercise) VALUES (1, 'EXERCISE_ANSWER_ID', 1, 1);" +
                        "INSERT INTO exercise_answer_answers (exercise_answer, question_answer) VALUES (1, 1), (1, 2);"
        );
    }

    protected void deleteData(DataSource ds) throws SQLException {
        ds.getConnection().createStatement().executeUpdate(
                "DELETE FROM exercise_answer_answers;" +
                        "DELETE FROM exercise_answer;" +
                        "DELETE FROM exercise_content;" +
                        "DELETE FROM question_answer;" +
                        "DELETE FROM chooseaword_content;" +
                        "DELETE FROM app_user;"
        );
    }

}