package language.contentandrepository.repository.textbook;

import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.persistence.dao.textbook.ChapterJpa;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import org.springframework.stereotype.Repository;
import language.contentandrepository.persistence.entity.textbook.ChapterEntity;

@Repository
public class ChapterRepository extends BaseContentRepository<Chapter, ChapterEntity> {

    public ChapterRepository(EntityToModelMapper<ChapterEntity, Chapter> entityToModelMapper,
                             ChapterJpa jpaRepository,
                             ChapterJpa.ID idsJpaRepository) {
        super(Chapter.class, entityToModelMapper, new DomainCache<Chapter>(), jpaRepository, idsJpaRepository);
    }

}
