package language.contentandrepository.model.domain.textbook;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.Teacher;

import java.time.LocalDateTime;
import java.util.List;

public record Textbook(
        DomainID<Textbook> id,
        String title,
        List<DomainID<Chapter>> chapters,
        DomainID<Teacher> author,
        LocalDateTime createdAt
) implements Domain {
}
