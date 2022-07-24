package model.input;

import model.domain.ID;
import model.domain.Question;
import model.domain.QuestionAnswer;
import model.domain.Student;

import java.util.List;

public record QuestionAnswerInput(
        ID<? extends Question> question,
        ID<Student> author,
        List<String> answers
) implements Input<QuestionAnswer> {

    public String baseId() {
        return "answer_for_" + question.id() + "_by_" + author.id();
    }

}
