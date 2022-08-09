package org.tutor.materials.textbook.model.domain.type.content;

import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;

public interface ChapterContent extends Domain {
    ID<? extends ChapterContent> id();
}
