package model.input;

import model.domain.ID;
import model.domain.QuestionAnswer;
import model.domain.QuestionEvaluation;
import model.domain.Teacher;

import java.io.Serializable;
import java.util.List;

public record QuestionEvaluationInput(
        ID<QuestionAnswer> answer,
        ID<Teacher> author,
        List<String> comments,
        int score
) implements Input<QuestionEvaluation> {

    @Override
    public String baseId() {
        return "evaluation_of_" + answer + "_by_" + author;
    }

}
