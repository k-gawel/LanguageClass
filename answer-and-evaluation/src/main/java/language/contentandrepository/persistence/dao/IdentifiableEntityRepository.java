package language.contentandrepository.persistence.dao;

import language.contentandrepository.persistence.entity.IdentifiableEntity;

import java.util.Optional;

public interface IdentifiableEntityRepository<E extends IdentifiableEntity> {

    Optional<E> findEntityById(String id);

    boolean deleteEntityById(String id);

}
