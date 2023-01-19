package language.contentandrepository.criteria.textbook;

import java.time.LocalDateTime;
import java.util.List;

public record TextbookCriteria(
        List<String> ids,
        String title,
        List<String> authors,
        List<String> allowedUsers,
        List<String> containingChapters,
        LocalDateTime after,
        LocalDateTime before
) {
}
