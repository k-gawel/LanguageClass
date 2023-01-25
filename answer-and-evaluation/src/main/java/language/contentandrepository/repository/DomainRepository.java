package language.contentandrepository.repository;

import language.contentandrepository.criteria.DomainCriteria;
import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import language.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface DomainRepository {

    <E extends Domain> Optional<E> find(DomainID<E> id);

    default <E extends Domain> E getOrThrow(DomainID<E> id) {
        return find(id).orElseThrow(() -> new NotFoundException(id));
    }

    default <E extends Domain> Optional<E> find(Class<E> domainClass, String id) {
        return find(new DomainID<>(domainClass, id));
    }

    default <E extends Domain> E getOrThrow(Class<E> domainClass, String id) {
        return getOrThrow(new DomainID<>(domainClass, id));
    }

    <E extends Domain> List<E> find(DomainCriteria<E> criteria);

}
