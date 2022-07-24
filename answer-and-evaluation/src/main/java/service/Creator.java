package service;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

abstract class Creator {

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

    private Long idCount(String id) {
        var query = "SELECT count(id) FROM " + tableName +  " WHERE id LIKE :baseId";
        var param = new MapSqlParameterSource()
                .addValue("baseId", id + "__%");
        return jdbcTemplate.queryForObject(query, param, Long.class);
    }

}
