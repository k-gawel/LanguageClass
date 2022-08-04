package model.repository.content;

import model.domain.ID;
import model.domain.content.ChooseAWordQuestion;
import model.domain.content.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class QuestionRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final Map<Class<? extends Question>, String> tableNames = Map.of(
            ChooseAWordQuestion.class, "chooseaword_content"
    );

    @Autowired
    public QuestionRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public <E extends Question> Optional<E> findById(ID<E> id) {
        if(id.type().equals(ChooseAWordQuestion.class))
            return (Optional<E>) findChooseAWord(id);
        else
            throw new IllegalArgumentException(id.toString());
    }


    private Optional<ChooseAWordQuestion> findChooseAWord(ID<? extends Question> id) {
        var query = "SELECT id, content_parts, correct_answers, word_choice " +
                "FROM chooseaword_content WHERE id = :id";
        var parameters = new MapSqlParameterSource()
                .addValue("id", id.id());
        var mapper = QuestionMapper.ChooseAWord;

        return jdbcTemplate.queryForStream(query, parameters, mapper).findFirst();
    }


    public Optional<Class<? extends Question>> findQuestionType(String stringId) {
        java.util.List<String> resultList = getResultFromDatabase(stringId);

        if(resultList.size() == 0)
            return Optional.empty();
        if(resultList.size() > 1)
            throw new IllegalStateException("Two different questions has the same id.");
        var className = resultList.get(0);
        return Optional.of(getClass(className));
    }

    public Optional<Long> findKey(ID<? extends Question> id) {
        var sql = """
                  SELECT key FROM
                  (
                    SELECT key, id FROM chooseaword_content
                    UNION ALL
                    SELECT key, id FROM fillaword_content
                  ) as kiki
                  WHERE kiki.id = :id
                  """;

        var parameters = new MapSqlParameterSource().addValue("id", id.id());

        return jdbcTemplate.queryForStream(sql, parameters, (r, i) -> r.getLong(1)).findFirst();
    }


    private List<String> getResultFromDatabase(String stringId) {
        var query = "SELECT 'ChooseAWordQuestion' as className FROM chooseaword_content WHERE id = :id " +
                    "UNION ALL " +
                    "SELECT 'FillAWordQuestion' as className FROM fillaword_content WHERE id = :id";
        var parameters = new MapSqlParameterSource().addValue("id", stringId);

        return jdbcTemplate.queryForList(query, parameters, String.class);
    }


    private Class<? extends Question> getClass(String className) {
        final String packageName = "model.domain.content.";
        var fullName = packageName + className;

        try {
            return (Class<? extends Question>) Class.forName(fullName);
        } catch (ClassCastException | ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find question type " + className, e);
        }
    }


}