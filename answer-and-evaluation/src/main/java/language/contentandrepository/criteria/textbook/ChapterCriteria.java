package language.contentandrepository.criteria.textbook;

import language.contentandrepository.criteria.DomainType;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.ChapterContent;
import language.contentandrepository.model.domain.textbook.Textbook;
import lombok.Builder;

import java.util.List;

public record ChapterCriteria(
        @DomainType(Chapter.class)
        List<String> ids,
        String name,
        @DomainType(ChapterContent.class)
        List<String> containingContent,
        @DomainType(Textbook.class)
        List<String> textbooks
) {

    @Builder public ChapterCriteria {}

}
