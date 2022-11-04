package language.contentandrepository.persistence.mapper.answerandevaluation;

import language.contentandrepository.persistence.dao.answerandevaluation.QuestionAnswerJpa;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import lombok.AllArgsConstructor;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.model.domain.user.Teacher;
import org.springframework.stereotype.Component;
import language.contentandrepository.persistence.dao.answerandevaluation.QuestionEvaluationJpa;
import language.contentandrepository.persistence.dao.user.TeacherJpa;
import language.contentandrepository.persistence.entity.answerandevaluation.QuestionEvaluationEntity;

@Component
@AllArgsConstructor
public class QuestionEvaluationMapper implements EntityToModelMapper<QuestionEvaluationEntity, QuestionEvaluation> {

    private final QuestionEvaluationJpa questionEvaluationJpa;
    private final QuestionAnswerJpa.ID questionAnswerIdJpa;
    private final TeacherJpa.ID teacherIdJpa;



    @Override
    public QuestionEvaluation fromEntity(QuestionEvaluationEntity entity) {
        return new QuestionEvaluation(
                new DomainID<>(QuestionEvaluation.class, entity.getId()),
                new DomainID<>(QuestionAnswer.class, entity.getAnswer().getId()),
                new DomainID<>(Teacher.class, entity.getTeacher().getId()),
                entity.getComments(),
                entity.getRating(),
                entity.getCreatedAt()
        );
    }

    @Override
    public QuestionEvaluationEntity toEntity(QuestionEvaluation model) {
        var entity = questionEvaluationJpa.findEntityById(model.id().id()).orElseGet(QuestionEvaluationEntity::new);

        entity.setId(model.id().id());
        entity.setAnswer(questionAnswerIdJpa.getIDById(model.answer().id()));
        entity.setComments(model.comments());
        entity.setRating(model.score());
        entity.setTeacher(teacherIdJpa.getIDById(model.author().id()));
        entity.setCreatedAt(model.createdAt());

        return entity;
    }

}
