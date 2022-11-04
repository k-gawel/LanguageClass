package language.contentandrepository.repository.textbook;

import language.contentandrepository.persistence.dao.textbook.ExerciseJpa;
import language.contentandrepository.persistence.entity.textbook.ExerciseEntity;
import language.contentandrepository.persistence.mapper.textbook.ExerciseEntityMapper;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseRepository extends BaseContentRepository<Exercise, ExerciseEntity> {

    public ExerciseRepository(ExerciseEntityMapper entityToModelMapper,
                              ExerciseJpa jpaRepository,
                              ExerciseJpa.ID idsJpaRepository) {
        super(Exercise.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
