package model.domain.answer;

import model.domain.Domain;
import model.domain.ID;
import model.domain.content.Question;
import model.domain.user.Student;

import java.sql.Timestamp;

import java.util.List;

public record QuestionAnswer(
        ID<QuestionAnswer> id,
        ID<? extends Question> question,
        ID<Student> student,
        List<String> answers,
        Timestamp createdAt
) implements Domain {
}
