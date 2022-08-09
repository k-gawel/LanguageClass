package org.tutor.materials.textbook.model.domain.type.content;

import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;

import java.util.Collection;
import java.util.List;

public record Textbook(
        ID<Textbook> id,
        String title,
        List<ID<Chapter>> chapters
) implements Domain {

    public Textbook(String id, String title, Collection<String> chapterIds) {
        this(new ID<>(Textbook.class, id), title, chapterIds.stream().map(i -> new ID<>(Chapter.class, i)).toList());
    }

}
