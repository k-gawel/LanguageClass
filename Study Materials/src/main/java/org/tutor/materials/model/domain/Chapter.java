package org.tutor.materials.model.domain;

import org.tutor.materials.model.domain.interfaces.ChapterPart;
import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.domain.interfaces.UUID;
import org.tutor.materials.model.domain.interfaces.ContentContainer;

import javax.annotation.Nonnull;
import java.util.List;

public record Chapter(@Nonnull UUID<Chapter> uuid,
                      String title,
                      List<UUID<ChapterPart>> content) implements Identifiable, ContentContainer<ChapterPart> {

}
