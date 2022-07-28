package model.repository;

import model.domain.ChooseAWordQuestion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class QuestionRepositoryTest {

    private QuestionRepository repository;
    private DataSource dataSource;

    @BeforeEach
    public void init() throws SQLException, ClassNotFoundException {
        this.dataSource = Provider.getDataSource();
        createData(dataSource);
        this.repository = new QuestionRepository(new NamedParameterJdbcTemplate(dataSource));
    }

    @AfterEach
    public void clean() throws SQLException {
        cleanData(dataSource);
    }

    @Test
    public void whenCorrectIdProvided_thenReturnsClass() {
        var id = "id1";
        var result = repository.findQuestionType(id);
        assertTrue(result.isPresent());
        assertEquals(ChooseAWordQuestion.class, result.get());
    }

    private void createData(DataSource dataSource) throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(
                "DELETE FROM chooseaword_content; DELETE FROM fillaword_content"
        );
        dataSource.getConnection().createStatement().executeUpdate(
                "INSERT INTO chooseaword_content (id) VALUES ('id1'), ('id2')");
        dataSource.getConnection().createStatement().executeUpdate(
                "INSERT INTO fillaword_content (id) VALUES ('id3'), ('id4')");
    }


    private void cleanData(DataSource ds) throws SQLException {
        ds.getConnection().createStatement().executeUpdate(
                "DELETE FROM chooseaword_content; DELETE FROM fillaword_content;"
        );
    }


}