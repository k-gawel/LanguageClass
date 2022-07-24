package model.repository;

import model.domain.ChooseAWordQuestion;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class QuestionRepositoryTest {

    private final QuestionRepository repository;

    QuestionRepositoryTest() throws ClassNotFoundException, SQLException {
        var dataSource = getDataSource();
        createData(dataSource);
        repository = new QuestionRepository(dataSource, chooseAWordRepository);
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
                "DROP TABLE IF EXISTS chooseaword_content; DROP TABLE IF EXISTS fillaword_content"
        );
        dataSource.getConnection().createStatement().executeUpdate(
                "CREATE TABLE chooseaword_content (id varchar(250))");
        dataSource.getConnection().createStatement().executeUpdate(
                "INSERT INTO chooseaword_content VALUES ('id1'), ('id2')");
        dataSource.getConnection().createStatement().executeUpdate(
                "CREATE TABLE fillaword_content (id varchar(250))");
        dataSource.getConnection().createStatement().executeUpdate(
                "INSERT INTO fillaword_content VALUES ('id3'), ('id4')");
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

}