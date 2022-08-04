package service;

import org.jooq.meta.derby.sys.Sys;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
class Creator {

    private final String tableName;
    protected final NamedParameterJdbcTemplate jdbcTemplate;

    protected Creator(String tableName, NamedParameterJdbcTemplate jdbcTemplate) {
        this.tableName = tableName;
        this.jdbcTemplate = jdbcTemplate;
    }

    protected String getId(String baseId) {
        var idsCount = idCount(baseId);
        return idsCount == 0 ? baseId : baseId + "__" + idsCount + 1;
    }

    protected Optional<Long> findKeyById(String tableName, String id) {
        System.out.println("FINDBYKEY " + tableName + " " + id);

        jdbcTemplate.query("SELECT * FROM " + tableName, Collections.emptyMap(), (r, i) -> r.getString("id")).forEach(System.out::println);

        var result = jdbcTemplate.queryForObject(
                "SELECT key FROM " + tableName + " WHERE id = :id",
                Map.of("id", id),
                Long.class
        );

        return Optional.ofNullable(result);
    }

    private Long idCount(String id) {
        var query = "SELECT count(id) FROM " + tableName +  " WHERE id LIKE :baseId";
        var param = new MapSqlParameterSource()
                .addValue("baseId", id + "__%");
        return jdbcTemplate.queryForObject(query, param, Long.class);
    }

}
