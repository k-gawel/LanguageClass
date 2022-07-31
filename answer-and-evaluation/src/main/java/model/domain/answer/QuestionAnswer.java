package model.domain.answer;

import model.domain.Domain;
import model.domain.ID;
import model.domain.content.Question;
import model.domain.user.Student;

import java.util.Date;
import java.util.List;

public record QuestionAnswer(
        ID<QuestionAnswer> id,
        ID<? extends Question> question,
        ID<Student> student,
        List<String> answers,
        Date createdAt
) implements Domain {
}
