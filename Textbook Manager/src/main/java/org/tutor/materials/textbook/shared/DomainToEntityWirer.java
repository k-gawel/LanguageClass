package org.tutor.materials.textbook.shared;

import org.jetbrains.annotations.NotNull;
import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.entity.content.ChapterEntity;
import org.tutor.materials.textbook.model.entity.content.TextbookEntity;
import org.tutor.materials.textbook.model.entity.shared.AbstractEntity;

import java.util.*;

public final class DomainToEntityWirer {

    private static final Map<Class<? extends Domain>, Class<? extends AbstractEntity>> entityRegistry = new HashMap<>();
    private static final Map<Class<? extends AbstractEntity>, List<Class<? extends Domain>>> domainRegistry = new HashMap<>();

    static {
        register(Textbook.class, TextbookEntity.class);
        register(Chapter.class, ChapterEntity.class);
    }

    public static void register(@NotNull Class<? extends Domain> domainClass, @NotNull Class<? extends AbstractEntity> entityClass) {
        entityRegistry.put(domainClass, entityClass);
        domainRegistry.putIfAbsent(entityClass, new ArrayList<>());
        domainRegistry.get(entityClass).add(domainClass);
    }

    public static Class<? extends AbstractEntity> getEntityClass(@NotNull Class<? extends Domain> domainClass) {
        return Optional
                .ofNullable(entityRegistry.get(domainClass))
                .orElseThrow(() -> new IllegalArgumentException(domainClass + " is not mapped to any entity."));
    }

    public static List<Class<? extends Domain>> getDomainClasses(@NotNull Class<? extends AbstractEntity> entityClass) {
        return domainRegistry.getOrDefault(entityClass, new ArrayList<>());
    }

}
