package language.contentandrepository.repository.impl.answerandevaluation;

import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.persistence.dao.answerandevaluation.QuestionAnswerJpa;
import language.contentandrepository.persistence.entity.answerandevaluation.QuestionAnswerEntity;
import language.contentandrepository.persistence.mapper.answerandevaluation.QuestionAnswerMapper;
import language.contentandrepository.repository.answerandevalution.QuestionAnswerRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "questionAnswerRepository")
public class BaseQuestionAnswerRepository extends BaseContentRepository<QuestionAnswer, QuestionAnswerEntity>
                                          implements QuestionAnswerRepository {

    public BaseQuestionAnswerRepository(QuestionAnswerMapper entityToModelMapper,
                                        QuestionAnswerJpa jpaRepository,
                                        QuestionAnswerJpa.ID idsJpaRepository) {
        super(QuestionAnswer.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
