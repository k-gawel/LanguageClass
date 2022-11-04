package language.contentandrepository.repository.textbook;

import language.contentandrepository.persistence.dao.textbook.ExampleJpa;
import language.contentandrepository.persistence.entity.textbook.ExampleEntity;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import language.contentandrepository.model.domain.textbook.Example;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleRepository extends BaseContentRepository<Example, ExampleEntity> {

    public ExampleRepository(EntityToModelMapper<ExampleEntity, Example> entityToModelMapper,
                             ExampleJpa jpaRepository,
                             ExampleJpa.ID idsJpaRepository) {
        super(Example.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
