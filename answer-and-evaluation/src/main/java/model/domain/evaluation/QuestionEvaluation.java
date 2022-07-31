package model.domain.evaluation;

import model.domain.Domain;
import model.domain.ID;
import model.domain.user.Teacher;
import model.domain.answer.QuestionAnswer;

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
