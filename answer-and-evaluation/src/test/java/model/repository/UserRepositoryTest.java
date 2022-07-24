package model.repository;

import model.domain.ID;
import model.domain.Student;
import model.domain.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryTest {

    private final DataSource dataSource;
    private final UserRepository repository;


    UserRepositoryTest() throws ClassNotFoundException, SQLException {
        dataSource = getDataSource();
        createDatas(dataSource);
        this.repository = new UserRepository(new NamedParameterJdbcTemplate(dataSource));
    }

    @Test
    public void when_correctIdOfTeacher_then_returnTeacher() {
        var teacherId = "user_id_1";
        var id = new ID<>(Teacher.class, teacherId);
        var result = repository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(new Teacher(new ID<>(Teacher.class, teacherId), "user_name_1"), result.get());
    }

    @Test
    public void when_teacherIdWithIncorrectType_then_returnEmpty() {
        var id = new ID<>(Teacher.class, "user_id_3");
        var result = repository.findById(id);
        assertTrue(result.isEmpty());
    }

    @Test
    public void when_teacherIdWithIncorrectId_then_returnEmpty() {
        var id = new ID<>(Teacher.class, "incorrect id");
        var result = repository.findById(id);
        assertTrue(result.isEmpty());
    }

    @Test
    public void when_correctIdOfStudent_then_returnStudent() {
        var id = new ID<>(Student.class, "user_id_3");
        var result = repository.findById(id);
        assertTrue(result.isPresent());
        assertEquals(new Student(id, "user_name_3"), result.get());
    }

    @Test
    public void when_studentIdWithIncorrectType_then_returnEmpty() {
        var id = new ID<>(Teacher.class, "user_id_3");
        var result = repository.findById(id);
        assertTrue(result.isEmpty());
    }

    @Test
    public void when_studentIdWithIncorrectId_then_returnEmpty() {
        var id = new ID<>(Student.class, "user_id_2");
        var result = repository.findById(id);
        assertTrue(result.isEmpty());
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

    public void createDatas(DataSource dataSource) throws  SQLException {
        Statement statement;

        statement = dataSource.getConnection().createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS app_user");
        statement.executeUpdate("" +
                "CREATE TABLE app_user (id varchar not null, name varchar not null, type varchar not null)");

        statement.executeUpdate(
                "INSERT INTO app_user VALUES " +
                        "('user_id_1', 'user_name_1', 'TEACHER')," +
                        "('user_id_2', 'user_name_2', 'TEACHER')," +
                        "('user_id_3', 'user_name_3', 'STUDENT')," +
                        "('user_id_4', 'user_name_4', 'STUDENT');"
        );

        var result = statement.executeQuery("SELECT * FROM app_user ORDER BY id");

        int i = 1;
        while (result.next()){
            assertEquals("user_id_" + i, result.getString("id"));
            i++;
        }
        assertEquals(5, i);
        result.close();
        statement.close();
    }

}