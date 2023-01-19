package language.contentandrepository.repository.impl.textbook;

import language.contentandrepository.persistence.dao.textbook.ExampleJpa;
import language.contentandrepository.persistence.entity.textbook.ExampleEntity;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import language.contentandrepository.model.domain.textbook.Example;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.repository.textbook.ExampleRepository;
import org.springframework.stereotype.Repository;

@Repository("exampleRepository")
public class BaseExampleRepository extends BaseContentRepository<Example, ExampleEntity>
                                   implements ExampleRepository {

    public BaseExampleRepository(EntityToModelMapper<ExampleEntity, Example> entityToModelMapper,
                                 ExampleJpa jpaRepository,
                                 ExampleJpa.ID idsJpaRepository) {
        super(Example.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
