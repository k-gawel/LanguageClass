package model.input;

import model.domain.ID;
import model.domain.answer.QuestionAnswer;
import model.domain.user.Teacher;

import java.util.List;

public record QuestionEvaluationInput(
        ID<QuestionAnswer> answer,
        ID<Teacher> author,
        List<String> comments,
        int score
) {


}
