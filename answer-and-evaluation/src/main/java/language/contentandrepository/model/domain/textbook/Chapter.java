package language.contentandrepository.model.domain.textbook;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;

import java.util.List;

public record Chapter(
        DomainID<Chapter> id,
        String title,
        List<DomainID<ChapterContent>> content
) implements Domain {
}
