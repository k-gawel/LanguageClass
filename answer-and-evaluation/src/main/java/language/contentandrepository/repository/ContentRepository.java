package language.contentandrepository.repository;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;

import java.util.List;
import java.util.Optional;

public interface ContentRepository<E extends Domain> {

    Optional<E> findById(DomainID<E> id);

    List<E> getByIds(List<DomainID<E>> ids);

    DomainID<E> generateId(String baseId);

    boolean add(E object);

    boolean save(E object);

    void delete(E object);

    boolean isPersisted(E object);

    boolean isPresent(E object);

}
