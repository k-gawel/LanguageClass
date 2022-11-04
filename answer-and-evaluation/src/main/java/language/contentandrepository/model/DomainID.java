package language.contentandrepository.model;

public record DomainID<E extends Domain>(
        Class<? extends E> type, String id
) {
}
