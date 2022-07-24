package model.repository;

import model.domain.ChooseAWordQuestion;
import model.domain.ID;
import model.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class QuestionRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ChooseAWordQuestionRepository chooseAWordRepository;

    @Autowired
    QuestionRepository(DataSource dataSource, ChooseAWordQuestionRepository chooseAWordRepository) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.chooseAWordRepository = chooseAWordRepository;
    }

    public Optional<? extends Question> findById(ID<? extends Question> id) {
        if(id.type().equals(ChooseAWordQuestion.class))
            return chooseAWordRepository.findById((ID<ChooseAWordQuestion>) id);
        else
            throw new IllegalArgumentException(id.toString());
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
        var table = Optional
                .ofNullable(tableName.get(id.type()))
                .orElseThrow(() -> new IllegalArgumentException(id.toString()));
        var sql = "SELECT key FROM " + table + " WHERE id = :id'";
        var parameters = new MapSqlParameterSource().addValue("id", id.id());

        return jdbcTemplate.queryForStream(sql, parameters, (r, i) -> r.getLong(0)).findFirst();
    }

    private List<String> getResultFromDatabase(String stringId) {
        var query = "SELECT 'ChooseAWordQuestion' as className FROM chooseaword_content WHERE id = :id " +
                    "UNION ALL " +
                    "SELECT 'FillAWordQuestion' as className FROM fillaword_content WHERE id = :id";
        var parameters = new MapSqlParameterSource().addValue("id", stringId);

        return jdbcTemplate.queryForList(query, parameters, String.class);
    }

    private Class<? extends Question> getClass(String className) {
        final String packageName = "model.domain.";
        var fullName = packageName + className;

        try {
            return (Class<? extends Question>) Class.forName(fullName);
        } catch (ClassCastException | ClassNotFoundException e) {
            throw new IllegalStateException("Cannot found question type. ", e);
        }
    }

    private static final Map<Class<? extends Question>, String> tableName = Map.of(
            ChooseAWordQuestion.class, "chooseaword_content"
    );

}
