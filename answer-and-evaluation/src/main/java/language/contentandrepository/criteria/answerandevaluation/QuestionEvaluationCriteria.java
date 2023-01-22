package language.contentandrepository.criteria.answerandevaluation;

import language.contentandrepository.criteria.DomainType;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionEvaluationCriteria(
        @DomainType(QuestionEvaluation.class)
        List<String> ids,
        @DomainType(QuestionAnswer.class)
        List<String> answers,
        @DomainType(Question.class)
        List<String> questions,
        @DomainType(Teacher.class)
        List<String> teachers,
        @DomainType(Student.class)
        List<String> students,
        LocalDateTime after,
        LocalDateTime before
) {

    @Builder public QuestionEvaluationCriteria {}

}
