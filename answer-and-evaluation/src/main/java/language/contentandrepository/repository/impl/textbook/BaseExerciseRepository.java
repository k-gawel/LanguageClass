package language.contentandrepository.repository.impl.textbook;

import language.contentandrepository.persistence.dao.textbook.ExerciseJpa;
import language.contentandrepository.persistence.entity.textbook.ExerciseEntity;
import language.contentandrepository.persistence.mapper.textbook.ExerciseEntityMapper;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.repository.textbook.ExerciseRepository;
import org.springframework.stereotype.Repository;

@Repository("exerciseRepository")
public class BaseExerciseRepository extends BaseContentRepository<Exercise, ExerciseEntity>
                                    implements ExerciseRepository {

    public BaseExerciseRepository(ExerciseEntityMapper entityToModelMapper,
                                  ExerciseJpa jpaRepository,
                                  ExerciseJpa.ID idsJpaRepository) {
        super(Exercise.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
