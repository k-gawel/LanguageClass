package language.contentandrepository.criteria.textbook;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record TextbookCriteria(
        List<String> ids,
        String title,
        List<String> authors,
        List<String> allowedUsers,
        List<String> containedChapters,
        LocalDateTime after,
        LocalDateTime before
) {

    @Builder(access = AccessLevel.PUBLIC)
    public TextbookCriteria {}

}
