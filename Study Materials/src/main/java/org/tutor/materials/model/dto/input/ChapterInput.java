package org.tutor.materials.model.dto.input;

import org.tutor.materials.model.domain.Chapter;

public record ChapterInput(long textbookId,
                             String title,
                             int number) implements Input<Chapter> {
}
