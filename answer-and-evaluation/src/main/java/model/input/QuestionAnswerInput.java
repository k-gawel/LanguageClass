package model.input;

import model.domain.ID;
import model.domain.content.Question;
import model.domain.answer.QuestionAnswer;
import model.domain.user.Student;

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
