package model.repository.criteria;

import lombok.Builder;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public record ExerciseAnswerCriteria(
        List<String> ids,
        List<String> authors,
        List<String> exercises,
        Timestamp startDate,
        Timestamp endDate,
        Integer offset,
        Integer limit
) implements Criteria {

    @Builder
    public ExerciseAnswerCriteria {
    }
}
