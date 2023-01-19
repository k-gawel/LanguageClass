package language.contentandrepository.repository.impl.answerandevaluation;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.persistence.dao.answerandevaluation.QuestionEvaluationJpa;
import language.contentandrepository.persistence.entity.answerandevaluation.QuestionEvaluationEntity;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.repository.answerandevalution.QuestionEvaluationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "questionEvaluationRepository")
public class BaseQuestionEvaluationRepository extends BaseContentRepository<QuestionEvaluation, QuestionEvaluationEntity>
                                              implements QuestionEvaluationRepository {

    public BaseQuestionEvaluationRepository(EntityToModelMapper<QuestionEvaluationEntity, QuestionEvaluation> entityToModelMapper,
                                            QuestionEvaluationJpa jpaRepository,
                                            QuestionEvaluationJpa.ID idsJpaRepository) {
        super(QuestionEvaluation.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

    public List<QuestionEvaluation> getEvaluationsByAnswer(DomainID<QuestionAnswer> id) {
        return domainCache.getAll().stream().filter(e -> e.answer().equals(id)).toList();
    }
}
