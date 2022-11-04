package language.contentandrepository.persistence.mapper.answerandevaluation;

import language.contentandrepository.persistence.dao.answerandevaluation.ExerciseAnswerJpa;
import language.contentandrepository.persistence.dao.answerandevaluation.QuestionAnswerJpa;
import language.contentandrepository.persistence.dao.textbook.ExerciseJpa;
import language.contentandrepository.persistence.dao.user.StudentJpa;
import language.contentandrepository.persistence.entity.answerandevaluation.ExerciseAnswerEntity;
import language.contentandrepository.persistence.entity.answerandevaluation.QuestionAnswerEntity;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.textbook.Exercise;
import lombok.AllArgsConstructor;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ExerciseAnswerMapper implements EntityToModelMapper<ExerciseAnswerEntity, ExerciseAnswer> {

    private ExerciseAnswerJpa exerciseAnswerJpa;
    private ExerciseJpa.ID exerciseIdJpa;
    private StudentJpa.ID studentIdJpa;
    private QuestionAnswerJpa.ID questionAnswerIdsJpa;

    @Override
    public ExerciseAnswer fromEntity(ExerciseAnswerEntity entity) {
        return new ExerciseAnswer(
                new DomainID<>(ExerciseAnswer.class, entity.getId()),
                new DomainID<>(Exercise.class, entity.getExercise().getId()),
                new DomainID<>(Student.class, entity.getStudent().getId()),
                entity.getQuestions().stream()
                        .map(i -> new DomainID<>(QuestionAnswer.class, i.getId()))
                        .toList(),
                entity.getCreatedAt()
        );
    }

    @Override
    public ExerciseAnswerEntity toEntity(ExerciseAnswer model) {
        var entity = exerciseAnswerJpa.findEntityById(model.id().id())
                .orElse(new ExerciseAnswerEntity());

        entity.setId(model.id().id());
        entity.setExercise(exerciseIdJpa.getIDById(model.exercise().id()));
        entity.setStudent(studentIdJpa.getIDById(model.author().id()));
        entity.setQuestions(getQuestionAnswerEntityIds(model.answers()));
        entity.setCreatedAt(model.createdAt());

        return entity;
    }

    private List<QuestionAnswerEntity.ID> getQuestionAnswerEntityIds(List<DomainID<QuestionAnswer>> domainIds) {
        return domainIds.stream()
                .map(DomainID::id)
                .map(questionAnswerIdsJpa::getIDById).toList();
    }

}
