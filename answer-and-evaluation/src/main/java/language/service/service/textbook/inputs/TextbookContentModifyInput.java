package language.service.service.textbook.inputs;

import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Textbook;

public record TextbookContentModifyInput(
        Textbook textbook,
        Chapter chapter,
        Integer newPlace
) {
}
