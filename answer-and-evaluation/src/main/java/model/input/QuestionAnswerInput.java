package model.input;

import model.domain.ID;
import model.domain.content.Question;
import model.domain.user.Student;

import java.util.List;

public record QuestionAnswerInput(
        ID<? extends Question> question,
        ID<Student> author,
        List<String> answers
) {
}
