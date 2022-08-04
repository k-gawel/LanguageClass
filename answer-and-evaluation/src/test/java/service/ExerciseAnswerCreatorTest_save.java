package service;

import model.domain.ID;
import model.domain.answer.ExerciseAnswer;
import model.domain.answer.QuestionAnswer;
import model.domain.content.Exercise;
import model.domain.user.Student;
import model.repository.*;
import model.repository.content.ExerciseRepository;
import model.repository.content.QuestionRepository;
import model.repository.content.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import utils.DummyClock;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Clock;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseAnswerCreatorTest_save {

    private final ExerciseAnswerRepository repository;
    private final ExerciseAnswerCreator creator;

    private final QuestionAnswerCreator questionAnswerCreator;
    private final Clock clock = new DummyClock();
    private final DataSource dataSource;

    ExerciseAnswerCreatorTest_save() {
        dataSource = Provider.getDataSource();
        var template = new NamedParameterJdbcTemplate(dataSource);
        this.repository = new ExerciseAnswerRepository(template);

        var questionRepository = new QuestionRepository(template);
        var userRepository = new UserRepository(template);

        this.creator = new ExerciseAnswerCreator(
                template,
                new ExerciseRepository(template),
                new QuestionAnswerRepository(template, questionRepository),
                userRepository,
                clock
        );
        this.questionAnswerCreator = new QuestionAnswerCreator(
                template,
                userRepository,
                questionRepository,
                clock);
    }

    @Test
    public void saveTest() {
        var id = new ID<>(ExerciseAnswer.class, "exercise_answer");

        var exerciseAnswer = new ExerciseAnswer(
                id,
                new ID<>(Exercise.class, "exercise"),
                new ID<>(Student.class, "student"),
                List.of(
                    new ID<>(QuestionAnswer.class, "questionAnswer1"),
                    new ID<>(QuestionAnswer.class, "questionAnswer2")
                ),
                new Timestamp(clock.millis())
        );

        assertTrue(repository.findById(id).isEmpty());

        creator.save(exerciseAnswer);

        System.out.println();
        var result = repository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(exerciseAnswer, result.get());
    }

    @BeforeEach
    public void createData() throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(
                """
                    INSERT INTO app_user (key, id, name, type)
                                  VALUES (1, 'teacher', 'teacher', 'TEACHER'),
                                         (2, 'student', 'student', 'STUDENT');
                    INSERT INTO chooseaword_content (key, id, content_parts, correct_answers, word_choice)
                                             VALUES (1, 'question1', '', '', ''),
                                                    (2, 'question2', '', '', '');
                    INSERT INTO question_answer (key, id, question, student)
                                         VALUES (1, 'questionAnswer1', 1, 2),
                                                (2, 'questionAnswer2', 2, 2);
                    INSERT INTO exercise_content (key, id, title, question_type)
                                          VALUES (1, 'exercise', 'content', 'choose_a_word');
                    """
        );
    }

    @AfterEach
    public void cleanData() throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(
                """
                    DELETE FROM exercise_answer_answers;
                    DELETE FROM exercise_answer;
                    DELETE FROM exercise_content;
                    DELETE FROM question_answer;
                    DELETE FROM chooseaword_content;
                    DELETE FROM app_user;
                    """
        );
    }

}