package language.contentandrepository.persistence.dao;

import language.contentandrepository.persistence.entity.IdentifiableEntity;

import javax.persistence.PersistenceException;
import java.util.Optional;

public interface EntityIdRepository<ID extends IdentifiableEntity> {

    Optional<ID> findIDById(String i);

    default ID getIDById(String id) {
        return findIDById(id).orElseThrow(() -> new PersistenceException("ExerciseAnswer '" + id + "' is not persisted."));
    }

}
