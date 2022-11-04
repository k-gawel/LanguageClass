package language.contentandrepository.repository.textbook;

import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.persistence.entity.textbook.TextbookEntity;
import language.contentandrepository.persistence.mapper.textbook.TextbookEntityMapper;
import language.contentandrepository.repository.BaseContentRepository;
import org.springframework.stereotype.Repository;
import language.contentandrepository.persistence.dao.textbook.TextbookJpa;

@Repository
public class TextbookRepository extends BaseContentRepository<Textbook, TextbookEntity> {

    public TextbookRepository(TextbookEntityMapper entityMapper,
                              TextbookJpa jpaRepository,
                              TextbookJpa.ID idsJpaRepository) {
        super(Textbook.class, entityMapper, new DomainCache<Textbook>(), jpaRepository, idsJpaRepository);
    }

}
