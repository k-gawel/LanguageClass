package language.contentandrepository.persistence.dao.textbook;

import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.dao.IdsJpaRepository;
import language.contentandrepository.persistence.entity.textbook.ChapterEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChapterJpa extends IdentifiableEntityJpaRepository<ChapterEntity> {

    @Override
    default Optional<ChapterEntity> findEntityById(String id) {
        return findChapterEntityById(id);
    }

    Optional<ChapterEntity> findChapterEntityById(String id);

    interface ID extends IdsJpaRepository<ChapterEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM ChapterEntity WHERE id LIKE '#1%%'")
        List<String> findIdsStartingWith(String beginString);

    }


}
