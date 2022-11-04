package language.contentandrepository.repository.answerandevalution;

import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.persistence.dao.answerandevaluation.ExerciseAnswerJpa;
import language.contentandrepository.persistence.entity.answerandevaluation.ExerciseAnswerEntity;
import org.springframework.stereotype.Repository;
import language.contentandrepository.persistence.mapper.answerandevaluation.ExerciseAnswerMapper;

@Repository
public class ExerciseAnswerRepository extends BaseContentRepository<ExerciseAnswer, ExerciseAnswerEntity> {

    public ExerciseAnswerRepository(ExerciseAnswerMapper entityToModelMapper,
                                    ExerciseAnswerJpa jpaRepository,
                                    ExerciseAnswerJpa.ID idsJpaRepository) {
        super(ExerciseAnswer.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
