package language.contentandrepository.persistence.mapper.textbook;

import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.ChapterContent;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.persistence.dao.textbook.ChapterJpa;
import lombok.AllArgsConstructor;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Example;
import org.springframework.stereotype.Component;
import language.contentandrepository.persistence.dao.textbook.ChapterContentJpa;
import language.contentandrepository.persistence.entity.textbook.ChapterContentEntity;
import language.contentandrepository.persistence.entity.textbook.ChapterEntity;
import language.contentandrepository.persistence.entity.enums.ChapterContentType;

import javax.persistence.PersistenceException;
import java.util.List;

@Component
@AllArgsConstructor
public class ChapterEntityMapper implements EntityToModelMapper<ChapterEntity, Chapter> {

    private final ChapterJpa chapterJpa;
    private final ChapterContentJpa.ID chapterContentIDJpa;

    @Override
    public Chapter fromEntity(ChapterEntity entity) {
        return new Chapter(
                new DomainID<>(Chapter.class, entity.getId()),
                entity.getTitle(),
                entity.getContent().stream().map(this::toDomainId)
                        .toList()
        );
    }

    @Override
    public ChapterEntity toEntity(Chapter model) {
        var entity = chapterJpa
                                    .findChapterEntityById(model.id().id())
                                    .orElse(new ChapterEntity());
        entity.setTitle(model.title());
        entity.setContent(getEntityIds(model.content()));

        return entity;
    }

    private List<ChapterContentEntity> getEntityIds(List<DomainID<ChapterContent>> ids) {
        return ids.stream()
                        .map(DomainID::id)
                        .map(i -> chapterContentIDJpa.findIDById(i)
                                                     .orElseThrow(() -> new PersistenceException("Chapter content '" + i + "' is not persisted" ))
                        )
                        .toList();
    }


    private DomainID<ChapterContent> toDomainId(ChapterContentEntity entityId) {
        var domainClass = toDomainClass(entityId.getChapterContentType());
        var idString = entityId.getId();
        return new DomainID<>(domainClass, idString);
    }

    private Class<? extends ChapterContent> toDomainClass(ChapterContentType type) {
        return switch (type) {
            case EXAMPLE -> Example.class;
            case EXERCISE -> Exercise.class;
        };
    }

}
