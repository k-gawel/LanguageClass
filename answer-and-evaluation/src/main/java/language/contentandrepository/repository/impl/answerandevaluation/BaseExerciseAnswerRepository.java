package language.contentandrepository.repository.impl.answerandevaluation;

import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.persistence.dao.answerandevaluation.ExerciseAnswerJpa;
import language.contentandrepository.persistence.entity.answerandevaluation.ExerciseAnswerEntity;
import language.contentandrepository.persistence.mapper.answerandevaluation.ExerciseAnswerMapper;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.repository.answerandevalution.ExerciseAnswerRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "exerciseAnswerRepository")
public class BaseExerciseAnswerRepository extends BaseContentRepository<ExerciseAnswer, ExerciseAnswerEntity>
                                          implements ExerciseAnswerRepository {


    public BaseExerciseAnswerRepository(ExerciseAnswerMapper entityToModelMapper,
                                        ExerciseAnswerJpa jpaRepository,
                                        ExerciseAnswerJpa.ID idsJpaRepository) {
        super(ExerciseAnswer.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }


}
