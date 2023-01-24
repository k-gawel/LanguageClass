package language.contentandrepository.criteria.textbook;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.ChapterContent;
import language.contentandrepository.model.domain.textbook.Textbook;
import lombok.Builder;

import java.util.List;

public record ChapterCriteria(
        List<DomainID<Chapter>> ids,
        String name,
        List<DomainID<ChapterContent>> containingContent,
        List<DomainID<Textbook>> textbooks
) {

    @Builder public ChapterCriteria {}

}
