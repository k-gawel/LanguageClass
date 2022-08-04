package model.repository;

import model.domain.ID;
import model.domain.user.Student;
import model.domain.user.Teacher;
import model.repository.content.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryTest {

    private UserRepository repository;
    private DataSource dataSource;

    @BeforeEach
    public void init() throws SQLException, ClassNotFoundException {
        var provided = Provider.getDataSource();
        this.dataSource = provided;
        createDatas(dataSource);
        this.repository = new UserRepository(new NamedParameterJdbcTemplate(dataSource));
    }

    @AfterEach
    public void clean() throws SQLException {
        cleanData(dataSource);
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

    public void createDatas(DataSource dataSource) throws  SQLException {
        Statement statement;

        statement = dataSource.getConnection().createStatement();
        statement.executeUpdate("DELETE FROM app_user");

        statement.executeUpdate(
                "INSERT INTO app_user (id, name, type) VALUES " +
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

    private void cleanData(DataSource ds) throws SQLException {
        ds.getConnection().createStatement().executeUpdate(
                "DELETE FROM app_user"
        );
    }

}