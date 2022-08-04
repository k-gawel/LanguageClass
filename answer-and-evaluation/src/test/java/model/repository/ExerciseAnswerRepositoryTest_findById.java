package model.repository;

import model.domain.ID;
import model.domain.answer.ExerciseAnswer;
import model.domain.answer.QuestionAnswer;
import model.domain.content.ChooseAWordQuestion;
import model.domain.content.Exercise;
import model.domain.user.Student;
import org.jooq.meta.derby.sys.Sys;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import utils.DummyClock;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseAnswerRepositoryTest_findById  {

    private final ExerciseAnswerRepository repository;
    private final DataSource dataSource;
    private final Clock clock = new DummyClock();

    ExerciseAnswerRepositoryTest_findById() {
        dataSource = Provider.getDataSource();
        repository = new ExerciseAnswerRepository(new NamedParameterJdbcTemplate(dataSource));
    }

    @Test
    public void findByIdTest() {
        var id = new ID<>(ExerciseAnswer.class, "EXERCISE_ANSWER_ID");

        var expected = new ExerciseAnswer(
                id,
                new ID<>(Exercise.class, "EXERCISE_ID"),
                new ID<>(Student.class, "STUDENT_ID"),
                List.of(
                        new ID<>(QuestionAnswer.class, "ANSWER_ID_1"),
                        new ID<>(QuestionAnswer.class, "ANSWER_ID_2")
                ),
                Timestamp.valueOf("2022-01-01 00:00:00")
        );

        var result = repository.findById(id);
        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @BeforeEach
    protected void createData() throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(
                    "INSERT INTO app_user (key, id, type) " +
                                      "VALUES (1, 'STUDENT_ID', 'STUDENT');" +
                        "INSERT INTO chooseaword_content (key, id) " +
                                                 "VALUES (1, 'QUESTION_ID_1'), " +
                                                        "(2, 'QUESTION_ID_2');" +
                        "INSERT INTO question_answer (key, id) " +
                                             "VALUES (1, 'ANSWER_ID_1'), " +
                                                    "(2, 'ANSWER_ID_2');" +
                        "INSERT INTO exercise_content (key, id, title, question_type) " +
                                              "VALUES (1, 'EXERCISE_ID', 'TITLE', 'choose_a_word');" +
                        "INSERT INTO exercise_answer (key, id, student, exercise, created_at) " +
                                             "VALUES (1, 'EXERCISE_ANSWER_ID', 1, 1, to_timestamp('2022-01-01', 'YYYY-MM-DD'));" +
                        "INSERT INTO exercise_answer_answers (exercise_answer, question_answer) " +
                                                     "VALUES (1, 1), (1, 2);"
        );
    }

    @AfterEach
    protected void deleteData() throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(
                "DELETE FROM exercise_answer_answers;" +
                "DELETE FROM exercise_answer;" +
                        "DELETE FROM exercise_content;" +
                        "DELETE FROM question_answer;" +
                        "DELETE FROM chooseaword_content;" +
                        "DELETE FROM app_user;"
        );
    }

}