package language.contentandrepository.model.domain.textbook;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;

import java.util.List;

public record Textbook(
        DomainID<Textbook> id,
        String title,
        List<DomainID<Chapter>> chapters
) implements Domain {
}
