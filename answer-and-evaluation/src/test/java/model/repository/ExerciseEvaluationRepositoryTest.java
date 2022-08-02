package model.repository;

import model.domain.ID;
import model.domain.answer.ExerciseAnswer;
import model.domain.evaluation.ExerciseEvaluation;
import model.domain.evaluation.QuestionEvaluation;
import model.domain.user.Teacher;
import model.repository.criteria.ExerciseEvaluationCriteria;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseEvaluationRepositoryTest extends AbstractRepositoryTest{

    private ExerciseEvaluationRepository repository;

    @BeforeEach
    public void init() throws SQLException, ClassNotFoundException {
        super.init();
        this.repository = new ExerciseEvaluationRepository(new NamedParameterJdbcTemplate(dataSource));
    }

    @AfterEach
    public void clean() throws SQLException {
        cleanData();
    }

    @Test
    public void findByIdTest() {
        ExerciseEvaluation result = (ExerciseEvaluation) repository.findById(new ID(ExerciseEvaluation.class, "eevaluation1")).orElseThrow();

        assertEquals("teacher", result.author().id());
        assertEquals("eevaluation1", result.id().id());
        assertEquals("comment1", result.comment());
        assertEquals(100, result.score());
        assertEquals(new ID(ExerciseAnswer.class, "eanswer1"), result.answer());
        assertEquals(List.of(
                new ID(QuestionEvaluation.class, "qevaluation1"),
                new ID(QuestionEvaluation.class, "qevaluation3"),
                new ID(QuestionEvaluation.class, "qevaluation4")
        ), result.questionEvaluations());
    }

    @Test
    public void findWithEmptyQuestions() {
        var expected =  new ExerciseEvaluation(
                new ID<>(ExerciseEvaluation.class, "eevaluation3"),
                new ID<>(Teacher.class, "teacher"),
                new ID<>(ExerciseAnswer.class, "eanswer2"),
                Collections.emptyList(),
                "comment3",
                30,
                null
        );

        var actual = repository.findById(new ID<>(ExerciseEvaluation.class, "eevaluation3")).orElseThrow();

        assertEquals(expected.id(), actual.id());
        assertEquals(expected.questionEvaluations(), actual.questionEvaluations());
        assertEquals(expected.answer(), actual.answer());
        assertEquals(expected.author(), actual.author());
        assertEquals(expected.comment(), actual.comment());
        assertEquals(expected.score(), actual.score());
    }

    @Test
    public void findByCriteriaTest() {
        var criteria = ExerciseEvaluationCriteria.builder().ids(List.of("eevaluation1", "eevaluation2")).build();

        var result = repository.find(criteria);

        assertEquals(2, result.size());
        var first = result.get(1);
        var second = result.get(0);

        var expectedFirst = new ExerciseEvaluation(
                new ID<>(ExerciseEvaluation.class, "eevaluation1"),
                new ID<>(Teacher.class, "teacher"),
                new ID<>(ExerciseAnswer.class, "eanswer1"),
                List.of(
                        new ID<>(QuestionEvaluation.class, "qevaluation1"),
                        new ID<>(QuestionEvaluation.class, "qevaluation3"),
                        new ID<>(QuestionEvaluation.class, "qevaluation4")
                ),
                "comment1",
                100,
                first.createdAt()
        );

        var expectedSecond = new ExerciseEvaluation(
                new ID<>(ExerciseEvaluation.class, "eevaluation2"),
                new ID<>(Teacher.class, "teacher"),
                new ID<>(ExerciseAnswer.class, "eanswer2"),
                List.of(
                        new ID<>(QuestionEvaluation.class, "qevaluation3"),
                        new ID<>(QuestionEvaluation.class, "qevaluation4"),
                        new ID<>(QuestionEvaluation.class, "qevaluation5")
                ),
                "comment2",
                50,
                second.createdAt()
        );

        assertEquals(expectedFirst, first);
        assertEquals(expectedSecond, second);
    }

    @Override
    protected void createData(DataSource dataSource) throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(
        """
            INSERT INTO app_user (key, id, name, type)
                          VALUES (1, 'student', 'teacher', 'STUDENT'),
                                 (2, 'teacher', 'teacher', 'TEACHER');
            INSERT INTO chooseaword_content(key, id, correct_answers, word_choice, content_parts)
                                    VALUES (1, 'question_1', '', '', ''),
                                           (2, 'question_2', '', '', ''),
                                           (3, 'question_3', '', '', ''),
                                           (4, 'question_4', '', '', '');
            INSERT INTO exercise_content(key, id, question_type, title) VALUES
                                        (1, 'exercise1', 'choose_a_word', ''),
                                        (2, 'exercise2', 'choose_a_word', '');
            INSERT INTO exercise_content_questions VALUES
                                        (1, 1), (1, 2), (1, 3),
                                        (2, 2), (2, 3), (2, 4);
            INSERT INTO question_answer (key, id, student, question, answers)
                                 VALUES (1, 'qanswer1', 1, 1, ''),
                                        (2, 'qanswer2', 1, 1, ''),
                                        (3, 'qanswer3', 1, 2, ''),
                                        (4, 'qanswer4', 1, 3, ''),
                                        (5, 'qanswer5', 1, 4, '');
            INSERT INTO exercise_answer (key, id, student, exercise)
                                 VALUES (1, 'eanswer1', 1, 1),
                                        (2, 'eanswer2', 1, 2);
            INSERT INTO exercise_answer_answers VALUES
                            (1, 1), (1, 3), (1, 4), (2, 3), (2, 4), (2, 5);
            INSERT INTO question_evaluation (key, id, teacher, answer, rating, comments)
                                     VALUES (1, 'qevaluation1', 2, 1, 100, ''),
                                            (2, 'qevaluation2', 2, 2, 100, ''),
                                            (3, 'qevaluation3', 2, 3, 100, ''),
                                            (4, 'qevaluation4', 2, 4, 100, ''),
                                            (5, 'qevaluation5', 2, 5, 100, '');
            INSERT INTO exercise_evaluation (key, id, comment, rating, answer, teacher)
                                     VALUES (1, 'eevaluation1', 'comment1', 100, 1, 2),
                                            (2, 'eevaluation2', 'comment2', 50, 2, 2),
                                            (3, 'eevaluation3', 'comment3', 30, 2, 2);
            INSERT INTO exercise_evaluation_question_evaluation VALUES
                                            (1, 1), (1, 3), (1, 4), (2, 3), (2, 4), (2, 5);
            """);
    }

    protected void cleanData() throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(
            """
            DELETE FROM exercise_evaluation_question_evaluation;
            DELETE FROM exercise_evaluation;
            DELETE FROM question_evaluation;
            DELETE FROM exercise_answer_answers;
            DELETE FROM exercise_answer;
            DELETE FROM question_answer;
            DELETE FROM exercise_content_questions;
            DELETE FROM exercise_content;
            DELETE FROM chooseaword_content;
            DELETE FROM app_user;
            """);
    }


}