package language.graphql.textbook.input;

import language.contentandrepository.model.domain.textbook.Chapter;

public record ChapterUpdateInput(
        Chapter chapter,
        String title
) {
}
