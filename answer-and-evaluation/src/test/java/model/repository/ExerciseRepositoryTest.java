package model.repository;

import model.domain.ID;
import model.domain.content.ChooseAWordQuestion;
import model.domain.content.Exercise;
import model.domain.content.QuestionType;
import model.repository.content.ExerciseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseRepositoryTest {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private ExerciseRepository exerciseRepository;

    @Test
    public void getByIdTest() {
        var id = new ID<>(Exercise.class, "exercise1");

        var expected = new Exercise(
                id,
                QuestionType.CHOOSE_A_WORD,
                List.of(
                        new ID<>(ChooseAWordQuestion.class, "question1"),
                        new ID<>(ChooseAWordQuestion.class, "question2"),
                        new ID<>(ChooseAWordQuestion.class, "question3")
                )
        );

        var result = exerciseRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @BeforeEach
    public void createData() throws SQLException {
        this.dataSource = Provider.getDataSource();
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.exerciseRepository = new ExerciseRepository(jdbcTemplate);


        dataSource.getConnection().createStatement().executeUpdate(
                """
                    INSERT INTO chooseaword_content (key, id, correct_answers, word_choice, content_parts)
                                             VALUES (1, 'question1', '', '', ''),
                                                    (2, 'question2', '', '', ''),
                                                    (3, 'question3', '', '', '');
                                                    
                    INSERT INTO exercise_content (key, id, title, question_type)
                                          VALUES (1, 'exercise1', 'exercise', 'choose_a_word');
                    
                    INSERT INTO exercise_content_questions VALUES (1, 1), (1, 2), (1, 3);
                    """
        );
    }

    @AfterEach
    public void clearData() throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(
                """
                    DELETE FROM exercise_content_questions;
                    DELETE FROM exercise_content;
                    DELETE FROM chooseaword_content;
                    """
        );
    }

}