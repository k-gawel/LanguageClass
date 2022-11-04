package language.contentandrepository.persistence.mapper.textbook;

import language.contentandrepository.persistence.entity.textbook.ExerciseEntity;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.question.QuestionType;
import language.contentandrepository.model.domain.textbook.Exercise;
import lombok.AllArgsConstructor;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.persistence.dao.textbook.ExerciseJpa;
import language.contentandrepository.persistence.dao.question.QuestionContentJpa;
import language.contentandrepository.persistence.entity.question.QuestionContentEntity;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.List;

@Component
@AllArgsConstructor
public class ExerciseEntityMapper implements EntityToModelMapper<ExerciseEntity, Exercise> {

    private final ExerciseJpa exerciseJpa;
    private final QuestionContentJpa.ID questionIdJpa;

    @Override
    public Exercise fromEntity(ExerciseEntity entity) {
        return new Exercise(
                new DomainID<>(Exercise.class, entity.getId()),
                entity.getTitle(),
                QuestionType.fromString(entity.getQuestionType()),
                entity.getQuestions().stream().map(this::getQuestionId).toList()
        );
    }

    @Override
    public ExerciseEntity toEntity(Exercise model) {
        var entity = exerciseJpa.findEntityById(model.id().id())
                .orElse(new ExerciseEntity());

        entity.setId(model.id().id());
        entity.setTitle(model.title());
        entity.setQuestionType(model.questionType().toString());
        entity.setQuestions(getEntityIds(model.questions()));

        return entity;
    }

    private DomainID<Question> getQuestionId(QuestionContentEntity.ID entityId) {
        var questionType = QuestionType.fromString(entityId.getType());
        var questionClass = questionType.questionClass();
        return new DomainID<>(questionClass, entityId.getId());
    }

    private List<QuestionContentEntity.ID> getEntityIds(List<DomainID<Question>> domainIDS) {
        return domainIDS.stream()
                .map(this::getEntityId)
                .toList();
    }

    private QuestionContentEntity.ID getEntityId(DomainID<Question> domainID) {
        var idString = domainID.id();
        return questionIdJpa.findIDById(idString)
                            .orElseThrow(() -> new PersistenceException("Question '" + idString + "' is not persisted."));
    }

}
