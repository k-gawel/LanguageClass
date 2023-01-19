package language.contentandrepository.repository.impl.answerandevaluation;

import language.contentandrepository.model.domain.question.ChooseAWord;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.persistence.dao.question.ChooseAWordQuestionJpa;
import language.contentandrepository.persistence.entity.question.ChooseAWordQuestionEntity;
import language.contentandrepository.persistence.mapper.question.ChooseAWordQuestionEntityMapper;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.question.ChooseAWordQuestionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BaseChooseAWordQuestionRepository extends BaseContentRepository<ChooseAWord, ChooseAWordQuestionEntity>
                                               implements ChooseAWordQuestionRepository {

    public BaseChooseAWordQuestionRepository(ChooseAWordQuestionEntityMapper entityToModelMapper,
                                             ChooseAWordQuestionJpa jpaRepository,
                                             ChooseAWordQuestionJpa.ID idsJpaRepository) {
        super(ChooseAWord.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
