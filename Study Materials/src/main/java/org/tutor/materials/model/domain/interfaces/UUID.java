package org.tutor.materials.model.domain.interfaces;


import java.util.Objects;

public record UUID<Q extends Identifiable>(java.util.UUID uuid,
                                           Class<Q> type) {

    public static <I extends Identifiable> UUID<I> random(Class<I> type) {
        return new UUID<I>(java.util.UUID.randomUUID(), type);
    }

    public UUID(String uuid, Class<Q> type) {
        this(java.util.UUID.fromString(uuid), type);
    }

    @Override
    public String toString() {
        return uuid.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UUID<?> uuid1 = (UUID<?>) o;
        return Objects.equals(uuid, uuid1.uuid) && Objects.equals(type, uuid1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, type);
    }
}
