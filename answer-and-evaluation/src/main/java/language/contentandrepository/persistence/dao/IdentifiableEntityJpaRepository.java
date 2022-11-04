package language.contentandrepository.persistence.dao;

import language.appconfig.config.IgnoreDuringScan;
import language.contentandrepository.persistence.entity.IdentifiableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@IgnoreDuringScan
public interface IdentifiableEntityJpaRepository<E extends IdentifiableEntity>
       extends JpaRepository<E, Long>,
               IdentifiableEntityRepository<E> {

    Optional<E> findEntityById(String id);

    default boolean deleteEntityById(String id) {
        return findEntityById(id)
                .map(i -> {
                    delete(i);
                    return true;
                })
                .orElse(false);
    }

}
