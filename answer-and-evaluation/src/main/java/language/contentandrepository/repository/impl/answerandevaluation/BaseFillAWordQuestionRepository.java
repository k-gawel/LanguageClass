package language.contentandrepository.repository.impl.answerandevaluation;

import language.contentandrepository.model.domain.question.FillAWord;
import language.contentandrepository.persistence.dao.question.FillAWordQuestionJpa;
import language.contentandrepository.persistence.entity.question.FillAWordQuestionEntity;
import language.contentandrepository.persistence.mapper.question.FillAWordQuestionEntityMapper;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.repository.question.FillAWordQuestionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BaseFillAWordQuestionRepository extends BaseContentRepository<FillAWord, FillAWordQuestionEntity>
                                             implements FillAWordQuestionRepository {

    public BaseFillAWordQuestionRepository(FillAWordQuestionEntityMapper entityToModelMapper,
                                           FillAWordQuestionJpa jpaRepository,
                                           FillAWordQuestionJpa.ID idsJpaRepository) {
        super(FillAWord.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }
}
