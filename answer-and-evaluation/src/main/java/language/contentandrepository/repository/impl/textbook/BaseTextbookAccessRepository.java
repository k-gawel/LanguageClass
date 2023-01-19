package language.contentandrepository.repository.impl.textbook;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.persistence.dao.textbook.TextbookJpa;
import language.contentandrepository.persistence.entity.textbook.TextbookAccess;
import language.contentandrepository.persistence.entity.textbook.TextbookEntity;
import language.contentandrepository.persistence.mapper.textbook.TextbookAccessEntityMapper;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.repository.textbook.TextbookAccessRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("textbookAccessRepository")
public class BaseTextbookAccessRepository extends BaseContentRepository<TextbookAccess, TextbookEntity>
                                          implements TextbookAccessRepository {

    public BaseTextbookAccessRepository(TextbookAccessEntityMapper entityToModelMapper,
                                        TextbookJpa jpaRepository,
                                        TextbookJpa.ID idsJpaRepository) {
        super(TextbookAccess.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

    @Override
    public Optional<TextbookAccess> findByTextbook(DomainID<Textbook> textbook) {
        return Optional.ofNullable(domainCache.getAll().stream().filter(a -> a.id().equals(textbook)).findFirst()
                .orElseGet(() -> jpaRepository.findEntityById(textbook.id()).map(entityToModelMapper::fromEntity).orElse(null)));
    }
}
