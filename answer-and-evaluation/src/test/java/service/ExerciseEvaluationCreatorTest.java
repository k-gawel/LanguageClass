package service;

import model.domain.ID;
import model.domain.answer.ExerciseAnswer;
import model.domain.evaluation.ExerciseEvaluation;
import model.domain.user.Teacher;
import model.input.ExerciseEvaluationInput;
import model.repository.ExerciseEvaluationRepository;
import model.repository.Provider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import utils.DummyClock;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Clock;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExerciseEvaluationCreatorTest {

    private final ExerciseEvaluationRepository repository;
    private final ExerciseEvaluationCreator creator;
    private final DataSource dataSource;

    private final Clock clock = new DummyClock();

    ExerciseEvaluationCreatorTest() {
        this.dataSource = Provider.getDataSource();
        var template = new NamedParameterJdbcTemplate(dataSource);
        this.repository = new ExerciseEvaluationRepository(template);
        this.creator = new ExerciseEvaluationCreator(template, clock);
    }

    @Test
    public void createTest() {
        var input = new ExerciseEvaluationInput(
                new ID<>(ExerciseAnswer.class, "answer"),
                new ID<>(Teacher.class, "teacher"),
                Collections.emptyList(),
                "comment",
                130
        );

        var expected = new ExerciseEvaluation(
                new ID<>(ExerciseEvaluation.class, "evaluation_for_answer_by_teacher"),
                new ID<>(Teacher.class, "teacher"),
                new ID<>(ExerciseAnswer.class, "answer"),
                Collections.emptyList(),
                "comment",
                130,
                new Timestamp(clock.millis())
        );
        var result = creator.create(input);

        assertEquals(expected, result);
    }

    @Test
    public void saveTest() {
        var id = new ID<>(ExerciseEvaluation.class, "evaluation_for_answer_by_teacher");

        assertTrue(repository.findById(id).isEmpty());

        var evaluation = new ExerciseEvaluation(
                id,
                new ID<>(Teacher.class, "teacher"),
                new ID<>(ExerciseAnswer.class, "answer"),
                Collections.emptyList(),
                "comment",
                130,
                new Timestamp(clock.millis())
        );

        creator.save(evaluation);

        var result = repository.findById(id).orElseThrow();

        assertEquals(evaluation, result);
    }


    @BeforeEach
    public void createData() throws SQLException {
        dataSource.getConnection().createStatement()
                .executeUpdate("""
                                      INSERT INTO app_user (key, id, name, type)
                                      VALUES (1, 'teacher', 'teacher', 'TEACHER'), (2, 'student', 'student', 'STUDENT');
                                      INSERT INTO exercise_content (key, id, title, question_type)
                                      VALUES (1, 'exercise', 'exercise_title', 'choose_a_word');
                                      INSERT INTO exercise_answer (key, id, exercise, student)
                                      VALUES (1, 'answer', 1, 2)
                                  """);
    }

    @AfterEach
    public void clearData() throws SQLException {
        dataSource.getConnection().createStatement()
                .executeUpdate(
                        "DELETE FROM exercise_evaluation;" +
                            "DELETE FROM exercise_answer;" +
                            "DELETE FROM exercise_content;" +
                            "DELETE FROM app_user;"
                );
    }

}