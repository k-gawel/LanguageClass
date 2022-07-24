package model.domain;

public record ID<E extends Domain>(
        Class<E> type, String id
) {
}
