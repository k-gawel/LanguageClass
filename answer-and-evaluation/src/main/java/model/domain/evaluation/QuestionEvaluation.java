package model.domain.evaluation;

import model.domain.Domain;
import model.domain.ID;
import model.domain.answer.QuestionAnswer;
import model.domain.user.Teacher;

import java.sql.Timestamp;
import java.util.List;

public record QuestionEvaluation(
        ID<QuestionEvaluation> id,
        ID<QuestionAnswer> answer,
        ID<Teacher> author,
        List<String> comments,
        int score,
        Timestamp createdAt
) implements Domain {
}
