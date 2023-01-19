package language.contentandrepository.repository.impl.textbook;

import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.persistence.entity.textbook.TextbookEntity;
import language.contentandrepository.persistence.mapper.textbook.TextbookEntityMapper;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.textbook.TextbookRepository;
import org.springframework.stereotype.Repository;
import language.contentandrepository.persistence.dao.textbook.TextbookJpa;

@Repository("textbookRepository")
public class BaseTextbookRepository extends BaseContentRepository<Textbook, TextbookEntity> implements TextbookRepository {

    public BaseTextbookRepository(TextbookEntityMapper entityMapper,
                                  TextbookJpa jpaRepository,
                                  TextbookJpa.ID idsJpaRepository) {
        super(Textbook.class, entityMapper, new DomainCache<Textbook>(), jpaRepository, idsJpaRepository);
    }

}
