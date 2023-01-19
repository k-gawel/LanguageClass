package language.contentandrepository.persistence.dao.answerandevaluation;

import language.contentandrepository.persistence.dao.IdsJpaRepository;
import org.springframework.data.jpa.repository.Query;
import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.entity.answerandevaluation.ExerciseAnswerEntity;

import java.util.List;
import java.util.Optional;

public interface ExerciseAnswerJpa extends IdentifiableEntityJpaRepository<ExerciseAnswerEntity> {

    @Override
    default Optional<ExerciseAnswerEntity> findEntityById(String id) {
        return findExerciseAnswerEntityById(id);
    }

    Optional<ExerciseAnswerEntity> findExerciseAnswerEntityById(String id);

    interface ID extends IdsJpaRepository<ExerciseAnswerEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM ExerciseAnswerEntity WHERE id LIKE concat(?1, '__%%')")
        List<String> findIdsStartingWith(String beginString);

    }

}