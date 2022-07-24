package model.repository;

import model.domain.AppUser;
import model.domain.ID;
import model.domain.Student;
import model.domain.Teacher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    static final Logger log = Logger.getLogger("Repository:User");

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<? extends AppUser> findById(ID<? extends AppUser> id) {
        var query = "SELECT id, name, type FROM app_user WHERE id = :id AND type = :type";
        var parameters = new MapSqlParameterSource()
                .addValue("id", id.id())
                .addValue("type", id.type().equals(Student.class) ? "STUDENT" : "TEACHER");
        return jdbcTemplate.queryForStream(query, parameters, mapper).findFirst();
    }

    private static final RowMapper<AppUser> mapper = (r, i) -> {
        var name = r.getString("name");
        var type = r.getString("type").equals("STUDENT") ? Student.class : Teacher.class;
        var id = new ID<>(type, r.getString("id"));
        return type.equals(Student.class) ?
                new Student((ID<Student>) id, name) : new Teacher((ID<Teacher>) id, name);
    };

    public Optional<Long> findKey(ID<? extends AppUser> id) {
        var query = "SELECT key FROM app_user WHERE id = :id AND type = :type";
        var param = new MapSqlParameterSource()
                .addValue("id", id.id())
                .addValue("type", id.type().equals(Student.class) ? "STUDENT" : "TEACHER");

        return jdbcTemplate.queryForStream(query, param, (r, i) -> r.getLong("key")).findFirst();
    }
}
