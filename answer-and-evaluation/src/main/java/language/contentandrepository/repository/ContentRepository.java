package language.contentandrepository.repository;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import language.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ContentRepository<E extends Domain> {

    List<E> find(Predicate<E> predicate);

    Optional<E> findById(DomainID<E> id);

    Optional<E> findById(String id);

    default E getById(DomainID<E> id) {
        return findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    default E getById(String id) {
        return findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    List<E> getByIds(List<DomainID<E>> ids);

    DomainID<E> generateId(String baseId);

    boolean add(E object);

    void delete(E object);

    boolean isPresent(E object);
}
