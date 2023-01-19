package utils.testmodels;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.user.Student;

import java.time.LocalDateTime;
import java.util.Collections;

public class TestQuestionAnswer {

    public static QuestionAnswer generate(Question question) {
        return new QuestionAnswer(
                TestModel.generateId(QuestionAnswer.class),
                question.id()   ,
                TestModel.generateId(Student.class),
                Collections.emptyList
                        (),
                LocalDateTime.now()
        );
    }

    public static QuestionAnswer generate(DomainID<Question> question, DomainID<Student> student) {
        return new QuestionAnswer(
                TestModel.generateId(QuestionAnswer.class),
                question,
                student,
                Collections.emptyList(),
                LocalDateTime.now()
        );
    }

}
