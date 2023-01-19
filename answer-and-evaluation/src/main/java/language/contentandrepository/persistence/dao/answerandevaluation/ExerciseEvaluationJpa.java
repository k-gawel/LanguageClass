package language.contentandrepository.persistence.dao.answerandevaluation;

import language.contentandrepository.persistence.dao.IdsJpaRepository;
import org.springframework.data.jpa.repository.Query;
import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.entity.answerandevaluation.ExerciseEvaluationEntity;

import java.util.List;
import java.util.Optional;

public interface ExerciseEvaluationJpa extends IdentifiableEntityJpaRepository<ExerciseEvaluationEntity> {

    @Override
    default Optional<ExerciseEvaluationEntity> findEntityById(String id) {
        return findExerciseEvaluationEntityById(id);
    }

    Optional<ExerciseEvaluationEntity> findExerciseEvaluationEntityById(String id);

    interface ID extends IdsJpaRepository<ExerciseEvaluationEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM ExerciseEvaluationEntity WHERE id LIKE concat(?1, '__%%')")
        List<String> findIdsStartingWith(String beginString);


    }
    
}
