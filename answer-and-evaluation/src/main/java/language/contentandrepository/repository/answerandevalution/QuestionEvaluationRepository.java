package language.contentandrepository.repository.answerandevalution;

import language.contentandrepository.persistence.dao.answerandevaluation.QuestionEvaluationJpa;
import language.contentandrepository.persistence.entity.answerandevaluation.QuestionEvaluationEntity;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.repository.DomainCache;
import org.springframework.stereotype.Repository;
import language.contentandrepository.repository.BaseContentRepository;

@Repository
public class QuestionEvaluationRepository extends BaseContentRepository<QuestionEvaluation, QuestionEvaluationEntity> {

    public QuestionEvaluationRepository(EntityToModelMapper<QuestionEvaluationEntity, QuestionEvaluation> entityToModelMapper,
                                        QuestionEvaluationJpa jpaRepository,
                                        QuestionEvaluationJpa.ID idsJpaRepository) {
        super(QuestionEvaluation.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
