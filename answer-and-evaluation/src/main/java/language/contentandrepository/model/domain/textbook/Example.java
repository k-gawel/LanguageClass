package language.contentandrepository.model.domain.textbook;

import language.contentandrepository.model.DomainID;

public record Example(
        DomainID<Example> id,
        String title,
        String content
) implements ChapterContent {
}
