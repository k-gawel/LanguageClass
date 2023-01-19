package language.contentandrepository.persistence.mapper.textbook;

import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Textbook;
import lombok.AllArgsConstructor;
import language.contentandrepository.persistence.dao.textbook.ChapterJpa;
import language.contentandrepository.persistence.dao.textbook.TextbookJpa;
import language.contentandrepository.persistence.entity.textbook.ChapterEntity;
import language.contentandrepository.persistence.entity.textbook.TextbookEntity;
import language.contentandrepository.model.DomainID;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.List;

@Component
@AllArgsConstructor
public class TextbookEntityMapper implements EntityToModelMapper<TextbookEntity, Textbook> {

    private final TextbookJpa textbookJpa;
    private final ChapterJpa.ID chapterIdJpa;

    @Override
    public Textbook fromEntity(TextbookEntity entity) {
        return new Textbook(
                new DomainID<>(Textbook.class, entity.getId()),
                entity.getTitle(),
                entity.getChapters().stream()
                        .map(i -> new DomainID<>(Chapter.class, i.getId()))
                        .toList(),
                new DomainID<>(Teacher.class, entity.getAuthor().getId()),
                entity.getCreatedAt()
        );
    }

    @Override
    public TextbookEntity toEntity(Textbook model) {
        var entity= textbookJpa.findEntityById(model.id().id())
                .map(currentEntity -> {
                    var result = new TextbookEntity();
                    result.setKey(currentEntity.getKey());
                    result.setPublic(currentEntity.isPublic());
                    result.setAllowedUsers(currentEntity.getAllowedUsers());
                    return result;
                })
                .orElse(new TextbookEntity());

        entity.setId(model.id().id());
        entity.setTitle(model.title());
        entity.setChapters(getChapterIds(model.chapters()));
        return entity;
    }

    private List<ChapterEntity.ID> getChapterIds(List<DomainID<Chapter>> chapterIds) {
        return chapterIds.stream()
                    .map(DomainID::id)
                    .map(idString -> chapterIdJpa.findIDById(idString)
                            .orElseThrow(() -> new PersistenceException("ChapterEntity with ID " + idString + " is not persisted."))
                    ).toList();
    }


}
