package language.contentandrepository.persistence.dao.textbook;

import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.dao.IdsJpaRepository;
import language.contentandrepository.persistence.entity.textbook.TextbookEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TextbookJpa extends IdentifiableEntityJpaRepository<TextbookEntity> {

    @Override
    default Optional<TextbookEntity> findEntityById(String id) {
        return findTextbookEntityById(id);
    }

    Optional<TextbookEntity> findTextbookEntityById(String id);

    interface ID extends IdsJpaRepository<TextbookEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM TextbookEntity WHERE id LIKE concat(?1, '__%%')")
        List<String> findIdsStartingWith(String beginString);

    }

}
