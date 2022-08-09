package org.tutor.materials.textbook.model.domain.type.content;

import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;

import java.util.List;

public record Chapter(
        ID<Chapter> id,
        String title,
        List<ID<? extends ChapterContent>> content
) implements Domain {
}
