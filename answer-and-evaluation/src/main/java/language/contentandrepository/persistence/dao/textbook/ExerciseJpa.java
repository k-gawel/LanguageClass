package language.contentandrepository.persistence.dao.textbook;


import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.dao.IdsJpaRepository;
import language.contentandrepository.persistence.entity.textbook.ExerciseEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExerciseJpa extends IdentifiableEntityJpaRepository<ExerciseEntity> {

    @Override
    default Optional<ExerciseEntity> findEntityById(String id) {
        return findExerciseEntityById(id);
    }

    Optional<ExerciseEntity> findExerciseEntityById(String id);

    interface ID extends IdsJpaRepository<ExerciseEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM ExerciseEntity WHERE id LIKE concat(?1, '__%%')")
        List<String> findIdsStartingWith(String beginString);

    }

}