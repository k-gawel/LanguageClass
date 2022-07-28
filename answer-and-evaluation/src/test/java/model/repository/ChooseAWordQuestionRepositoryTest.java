package model.repository;

import model.domain.ChooseAWordQuestion;
import model.domain.ID;
import model.repository.utils.Converter;
import org.jooq.meta.derby.sys.Sys;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChooseAWordQuestionRepositoryTest extends AbstractRepositoryTest {

    private QuestionRepository repository;

    @BeforeEach
    @Override
    public void init() throws SQLException, ClassNotFoundException {
        super.init();
        this.repository = new QuestionRepository(new NamedParameterJdbcTemplate(dataSource));
    }

    @AfterEach
    public void clean() throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(
                "DELETE FROM chooseaword_content;"
        );
    }

    @Test
    public void whenCorrectId_thenReturnQuestion() {
        var id = new ID<>(ChooseAWordQuestion.class, "id_1");
        var result = repository.findById(id);
        assertTrue(result.isPresent());
        assertEquals(id, result.get().id());
        assertEquals(List.of(List.of("word1")), result.get().correctAnswers());
    }


    public void createData(DataSource dataSource) throws SQLException {
        Statement statement;
        statement = dataSource.getConnection().createStatement();
        statement.executeUpdate("DELETE FROM chooseaword_content");

        var contentPart1 = Converter.ToDatabase.stringList(toList("part1", null, "part2"));
        var wordChoice1 = Converter.ToDatabase.nestedStringList(List.of(List.of("word1", "word2")));
        var correctAnswers1 = Converter.ToDatabase.nestedStringList(List.of(List.of("word1")));

        var contentPart2 = Converter.ToDatabase.stringList(toList(null, "part1", "part2", null));
        var wordChoice2 = Converter.ToDatabase.nestedStringList(List.of(List.of("word1", "word2"), List.of("word3", "word4")));
        var correctAnswers2 = Converter.ToDatabase.nestedStringList(List.of(List.of("word1"), List.of("word4")));

        statement.executeUpdate(
                "INSERT INTO chooseaword_content (id, content_parts, correct_answers, word_choice) " +
                        "VALUES " +
                        "('id_1', '" + contentPart1 + "', '" + correctAnswers1 + "', '" + wordChoice1 + "')," +
                        "('id_2', '" + contentPart2 + "', '" + correctAnswers2 + "', '" + wordChoice2 + "');"
        );

        var result = statement.executeQuery("SELECT * FROM chooseaword_content ORDER BY id");

        int i = 1;
        while (result.next()){
            assertEquals("id_" + i, result.getString("id"));
            i++;
        }
        assertEquals(3, i);
        result.close();
        statement.close();
        dataSource.getConnection().close();
    }

    private List<String> toList(String... element) {
        var result = new ArrayList<String>();
        Collections.addAll(result, element);
        return result;
    }

}