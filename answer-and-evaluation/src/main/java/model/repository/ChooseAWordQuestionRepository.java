package model.repository;

import model.domain.ChooseAWordQuestion;
import model.domain.ID;
import model.repository.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class ChooseAWordQuestionRepository  {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ChooseAWordQuestionRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Optional<ChooseAWordQuestion> findById(ID<ChooseAWordQuestion> id) {
        var query = "SELECT id, content_parts, correct_answers, word_choice " +
                    "FROM chooseaword_content WHERE id = :id";
        var parameters = new MapSqlParameterSource()
                .addValue("id", id.id());

        return jdbcTemplate.queryForStream(query, parameters, mapper).findFirst();
    }

    private final static RowMapper<ChooseAWordQuestion> mapper = (r, i) -> {
        var wordChoiceString = r.getString("word_choice");
        var id = new ID<>(ChooseAWordQuestion.class, r.getString("id"));
        var correctAnswersString = r.getString("correct_answers");
        var contentPartsString = r.getString("content_parts");

        return new ChooseAWordQuestion(
                id,
                Converter.FromDatabase.stringList(contentPartsString),
                Converter.FromDatabase.nestedStringList(wordChoiceString),
                Converter.FromDatabase.nestedStringList(correctAnswersString)
        );
    };


}
