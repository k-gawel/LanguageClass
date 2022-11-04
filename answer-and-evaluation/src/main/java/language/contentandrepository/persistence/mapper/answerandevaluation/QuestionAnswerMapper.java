package language.contentandrepository.persistence.mapper.answerandevaluation;

import language.contentandrepository.persistence.dao.answerandevaluation.QuestionAnswerJpa;
import lombok.AllArgsConstructor;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.user.Student;
import org.springframework.stereotype.Component;
import language.contentandrepository.persistence.dao.question.QuestionContentJpa;
import language.contentandrepository.persistence.dao.user.StudentJpa;
import language.contentandrepository.persistence.entity.answerandevaluation.QuestionAnswerEntity;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import language.contentandrepository.persistence.mapper.question.QuestionIdMapper;

@Component
@AllArgsConstructor
public class QuestionAnswerMapper implements EntityToModelMapper<QuestionAnswerEntity, QuestionAnswer> {

    private QuestionAnswerJpa questionAnswerJpa;
    private QuestionContentJpa.ID questionContentIdJpa;
    private StudentJpa.ID studentIdJpa;

    @Override
    public QuestionAnswer fromEntity(QuestionAnswerEntity entity) {
        return new QuestionAnswer(
                new DomainID<>(QuestionAnswer.class, entity.getId()),
                QuestionIdMapper.toDomain(entity.getQuestion()),
                new DomainID<>(Student.class, entity.getStudent().getId()),
                entity.getAnswers().stream().toList(),
                entity.getCreatedAt()
        );
    }

    @Override
    public QuestionAnswerEntity toEntity(QuestionAnswer model) {
        var entity = questionAnswerJpa.findEntityById(model.id().id())
                .orElse(new QuestionAnswerEntity());

        entity.setId(model.id().id());
        entity.setAnswers(model.answers().stream().toList());
        entity.setQuestion(questionContentIdJpa.getIDById(model.question().id()));
        entity.setStudent(studentIdJpa.getIDById(model.student().id()));
        entity.setCreatedAt(model.createdAt());

        return entity;
    }

}
