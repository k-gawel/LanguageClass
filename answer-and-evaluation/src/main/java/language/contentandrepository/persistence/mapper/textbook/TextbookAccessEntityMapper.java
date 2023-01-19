package language.contentandrepository.persistence.mapper.textbook;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.persistence.dao.textbook.TextbookJpa;
import language.contentandrepository.persistence.dao.user.StudentJpa;
import language.contentandrepository.persistence.entity.IdentifiableEntity;
import language.contentandrepository.persistence.entity.textbook.TextbookAccess;
import language.contentandrepository.persistence.entity.textbook.TextbookEntity;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TextbookAccessEntityMapper implements EntityToModelMapper<TextbookEntity, TextbookAccess> {

    private final TextbookJpa textbookJpa;
    private final StudentJpa.ID studentIdJpa;

    @Override
    public TextbookAccess fromEntity(TextbookEntity entity) {
        return new TextbookAccess(
                new DomainID<>(Textbook.class, entity.getId()),
                entity.isPublic(),
                entity.getAllowedUsers().stream()
                        .map(IdentifiableEntity::getId)
                        .map(i -> new DomainID<>(Student.class, i))
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public TextbookEntity toEntity(TextbookAccess model) {
        var entity = textbookJpa.findEntityById(model.id().id())
                .orElseThrow(() -> new EntityNotFoundException(model.id().toString()));

        var allowedUsers = model.allowedStudents()
                .stream()
                .map(i -> studentIdJpa.findIDById(i.id()).orElseThrow(() -> new EntityNotFoundException(i.toString())))
                .collect(Collectors.toSet());


        entity.setAllowedUsers(allowedUsers);
        entity.setPublic(model.isPublic());

        return entity;
    }

}
