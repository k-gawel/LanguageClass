package language.contentandrepository.repository.question;

import language.contentandrepository.model.domain.question.ChooseAWord;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.persistence.dao.question.ChooseAWordQuestionJpa;
import language.contentandrepository.persistence.entity.question.ChooseAWordQuestionEntity;
import language.contentandrepository.persistence.mapper.question.ChooseAWordQuestionEntityMapper;
import language.contentandrepository.repository.BaseContentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ChooseAWordQuestionRepository extends BaseContentRepository<ChooseAWord, ChooseAWordQuestionEntity> {

    public ChooseAWordQuestionRepository(ChooseAWordQuestionEntityMapper entityToModelMapper,
                                         ChooseAWordQuestionJpa jpaRepository,
                                         ChooseAWordQuestionJpa.ID idsJpaRepository) {
        super(ChooseAWord.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
