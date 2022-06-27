package org.tutor.materials.model.domain;

import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.domain.interfaces.UUID;
import org.tutor.materials.model.domain.interfaces.ContentContainer;

import java.util.Collections;
import java.util.List;

public record Textbook(UUID<Textbook> uuid,
                       String name,
                       List<UUID<Chapter>> chapters) implements Identifiable, ContentContainer<Chapter> {

    @Override
    public List<UUID<Chapter>> content() {
        return chapters;
    }

    public Textbook(java.util.UUID uuid, String name) {
        this(new UUID<Textbook>(uuid, Textbook.class), name, Collections.emptyList());
    }

}
