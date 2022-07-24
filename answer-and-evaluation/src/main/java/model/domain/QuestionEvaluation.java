package model.domain;

import java.util.Date;
import java.util.List;

public record QuestionEvaluation(
        ID<QuestionEvaluation> id,
        ID<QuestionAnswer> answer,
        ID<Teacher> author,
        List<String> comments,
        int score,
        Date createdAt
) implements Domain {
}
