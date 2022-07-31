package model.repository.criteria;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public record ExerciseEvaluationCriteria(
        List<String> ids,
        List<String> authors,
        List<String> answers,
        List<String> students,
        Timestamp startDate,
        Timestamp endDate,
        Integer limit,
        Integer offset
) implements Criteria {
}
