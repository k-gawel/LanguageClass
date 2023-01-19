package language.contentandrepository.criteria.textbook;

import lombok.Builder;

import java.util.List;

public record ChapterCriteria(
        List<String> ids,
        String name,
        List<String> containingContent,
        List<String> textbooks
) {

    @Builder public ChapterCriteria {}

}
