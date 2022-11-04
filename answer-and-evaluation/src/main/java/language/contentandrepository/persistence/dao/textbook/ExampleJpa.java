package language.contentandrepository.persistence.dao.textbook;

import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.dao.IdsJpaRepository;
import language.contentandrepository.persistence.entity.textbook.ExampleEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExampleJpa extends IdentifiableEntityJpaRepository<ExampleEntity> {

    @Override
    default Optional<ExampleEntity> findEntityById(String id) {
        return findExampleEntityById(id);
    }

    Optional<ExampleEntity> findExampleEntityById(String id);

    interface ID extends IdsJpaRepository<ExampleEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM ExampleEntity WHERE id LIKE '#1%%'")
        List<String> findIdsStartingWith(String beginString);

    }

}
