package language.contentandrepository.repository.answerandevalution;

import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.persistence.dao.answerandevaluation.QuestionAnswerJpa;
import language.contentandrepository.persistence.entity.answerandevaluation.QuestionAnswerEntity;
import language.contentandrepository.persistence.mapper.answerandevaluation.QuestionAnswerMapper;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionAnswerRepository extends BaseContentRepository<QuestionAnswer, QuestionAnswerEntity> {

    public QuestionAnswerRepository(QuestionAnswerMapper entityToModelMapper,
                                    QuestionAnswerJpa jpaRepository,
                                    QuestionAnswerJpa.ID idsJpaRepository) {
        super(QuestionAnswer.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
