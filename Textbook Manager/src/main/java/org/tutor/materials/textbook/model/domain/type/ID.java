package org.tutor.materials.textbook.model.domain.type;

import javax.annotation.Nonnull;

public record ID<Q extends Domain>(
        @Nonnull Class<Q> type,
        @Nonnull String id
) {

    @Override
    public String toString() {
        return "{" + type.getSimpleName() + ": " + id + "}";
    }
}
