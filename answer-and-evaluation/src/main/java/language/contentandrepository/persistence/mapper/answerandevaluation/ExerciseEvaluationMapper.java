package language.contentandrepository.persistence.mapper.answerandevaluation;

import lombok.AllArgsConstructor;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseEvaluation;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.model.domain.user.Teacher;
import org.springframework.stereotype.Component;
import language.contentandrepository.persistence.dao.answerandevaluation.ExerciseAnswerJpa;
import language.contentandrepository.persistence.dao.answerandevaluation.ExerciseEvaluationJpa;
import language.contentandrepository.persistence.dao.answerandevaluation.QuestionEvaluationJpa;
import language.contentandrepository.persistence.dao.user.TeacherJpa;
import language.contentandrepository.persistence.entity.IdentifiableEntity;
import language.contentandrepository.persistence.entity.answerandevaluation.ExerciseEvaluationEntity;
import language.contentandrepository.persistence.entity.answerandevaluation.QuestionEvaluationEntity;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;

import java.util.List;

@Component
@AllArgsConstructor
public class ExerciseEvaluationMapper implements EntityToModelMapper<ExerciseEvaluationEntity, ExerciseEvaluation> {

    private final ExerciseEvaluationJpa exerciseEvaluationJpa;
    private final ExerciseAnswerJpa.ID exerciseAnswerIdJpa;
    private final QuestionEvaluationJpa.ID questionEvaluationIdJpa;
    private final TeacherJpa.ID teacherIdJpa;

    @Override
    public ExerciseEvaluation fromEntity(ExerciseEvaluationEntity entity) {
        return new ExerciseEvaluation(
                new DomainID<>(ExerciseEvaluation.class, entity.getId()),
                new DomainID<>(Teacher.class, entity.getTeacher().getId()),
                new DomainID<>(ExerciseAnswer.class, entity.getAnswer().getId()),
                entity.getQuestionEvaluations().stream()
                        .map(IdentifiableEntity::getId)
                        .map(i -> new DomainID<>(QuestionEvaluation.class, i))
                        .toList(),
                entity.getComment(),
                entity.getRating(),
                entity.getCreatedAt()
        );
    }

    @Override
    public ExerciseEvaluationEntity toEntity(ExerciseEvaluation model) {
        var entity = exerciseEvaluationJpa.findEntityById(model.id().id())
                .orElseGet(ExerciseEvaluationEntity::new);

        entity.setId(model.id().id());
        entity.setAnswer(exerciseAnswerIdJpa.getIDById(model.answer().id()));
        entity.setComment(model.comment());
        entity.setQuestionEvaluations(getQuestionEvaluationEntityIds(model.questionEvaluations()));
        entity.setTeacher(teacherIdJpa.getIDById(model.author().id()));
        entity.setRating(model.score());
        entity.setCreatedAt(model.createdAt());

        return entity;
    }

    private List<QuestionEvaluationEntity.ID> getQuestionEvaluationEntityIds(List<DomainID<QuestionEvaluation>> domainIds) {
        return domainIds.stream()
                .map(DomainID::id)
                .map(questionEvaluationIdJpa::getIDById)
                .toList();
    }

}
