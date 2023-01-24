package language.contentandrepository.criteria.answerandevaluation;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionEvaluationCriteria(
        List<DomainID<QuestionEvaluation>> ids,
        List<DomainID<QuestionAnswer>> answers,
        List<DomainID<Question>> questions,
        List<DomainID<Teacher>> teachers,
        List<DomainID<Student>> students,
        LocalDateTime after,
        LocalDateTime before
) {

    @Builder public QuestionEvaluationCriteria {}

}
