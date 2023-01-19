package language.service.service.textbook.inputs;

import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.ChapterContent;

public record ChapterContentModifyInput(
        Chapter chapter,
        ChapterContent chapterContent,
        Integer newPlace
) {
}
