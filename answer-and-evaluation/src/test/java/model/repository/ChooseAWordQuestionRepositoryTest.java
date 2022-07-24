package model.repository;

import model.domain.ChooseAWordQuestion;
import model.domain.ID;
import model.repository.utils.Converter;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChooseAWordQuestionRepositoryTest {

    private final DataSource dataSource;
    private final ChooseAWordQuestionRepository repository;

    public ChooseAWordQuestionRepositoryTest() throws ClassNotFoundException, SQLException {
        dataSource = getDataSource();
        createDatas(dataSource);
        repository = new ChooseAWordQuestionRepository(new NamedParameterJdbcTemplate(dataSource));
    }


    @Test
    public void whenCorrectId_thenReturnQuestion() {
        var id = new ID<>(ChooseAWordQuestion.class, "id_1");
        var result = repository.findById(id);
        assertTrue(result.isPresent());
        assertEquals(id, result.get().id());
        assertEquals(List.of(List.of("word1")), result.get().correctAnswers());
    }

    private DataSource getDataSource() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:h2:~/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    public void createDatas(DataSource dataSource) throws SQLException {
        Statement statement;

        statement = dataSource.getConnection().createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS chooseaword_content");
        statement.executeUpdate("" +
                "CREATE TABLE chooseaword_content (id varchar not null, content_parts varchar not null, correct_answers varchar not null, word_choice varchar not null )");

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
    }

    private List<String> toList(String... element) {
        var result = new ArrayList<String>();
        for (String s : element) {
            result.add(s);
        }
        return result;
    }

}