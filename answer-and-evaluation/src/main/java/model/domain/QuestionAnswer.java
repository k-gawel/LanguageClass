package model.domain;

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
