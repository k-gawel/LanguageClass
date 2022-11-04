package language.contentandrepository.persistence.mapper.textbook;

import language.contentandrepository.persistence.dao.textbook.ExampleJpa;
import language.contentandrepository.persistence.entity.textbook.ExampleEntity;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import language.contentandrepository.model.domain.textbook.Example;
import lombok.AllArgsConstructor;
import language.contentandrepository.model.DomainID;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExampleEntityMapper implements EntityToModelMapper<ExampleEntity, Example> {

    private final ExampleJpa exampleJpa;

    @Override
    public Example fromEntity(ExampleEntity entity) {
        return new Example(
                new DomainID<>(Example.class, entity.getId()),
                entity.getTitle(),
                entity.getContent()
        );
    }

    @Override
    public ExampleEntity toEntity(Example model) {
        var entity = exampleJpa.findEntityById(model.id().id())
                .orElse(new ExampleEntity());

        entity.setId(model.id().id());
        entity.setTitle(model.title());
        entity.setContent(model.content());

        return entity;
    }

}
