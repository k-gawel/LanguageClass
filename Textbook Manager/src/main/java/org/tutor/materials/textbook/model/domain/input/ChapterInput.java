package org.tutor.materials.textbook.model.domain.input;

import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;

public record ChapterInput(
        ID<Textbook> textbook,
        String title,
        Integer place
) {
}
