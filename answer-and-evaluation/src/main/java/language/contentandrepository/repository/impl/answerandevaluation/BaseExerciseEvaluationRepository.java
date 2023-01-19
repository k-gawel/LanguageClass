package language.contentandrepository.repository.impl.answerandevaluation;

import language.contentandrepository.model.domain.answerandevaluation.ExerciseEvaluation;
import language.contentandrepository.persistence.dao.answerandevaluation.ExerciseEvaluationJpa;
import language.contentandrepository.persistence.entity.answerandevaluation.ExerciseEvaluationEntity;
import language.contentandrepository.persistence.mapper.answerandevaluation.ExerciseEvaluationMapper;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.repository.answerandevalution.ExerciseEvaluationRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "exerciseEvaluationRepository")
public class BaseExerciseEvaluationRepository extends BaseContentRepository<ExerciseEvaluation, ExerciseEvaluationEntity>
                                              implements ExerciseEvaluationRepository {

    public BaseExerciseEvaluationRepository(ExerciseEvaluationMapper entityToModelMapper,
                                            ExerciseEvaluationJpa jpaRepository,
                                            ExerciseEvaluationJpa.ID idsJpaRepository) {
        super(ExerciseEvaluation.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
