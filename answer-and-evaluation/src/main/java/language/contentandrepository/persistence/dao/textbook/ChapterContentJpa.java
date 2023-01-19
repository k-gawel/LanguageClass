package language.contentandrepository.persistence.dao.textbook;

import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.dao.IdsJpaRepository;
import language.contentandrepository.persistence.entity.textbook.ChapterContentEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChapterContentJpa extends IdentifiableEntityJpaRepository<ChapterContentEntity> {

    @Override
    default Optional<ChapterContentEntity> findEntityById(String id) {
        return findChapterContentEntityById(id);
    }

    Optional<ChapterContentEntity> findChapterContentEntityById(String id);

    interface ID extends IdsJpaRepository<ChapterContentEntity> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM ChapterContentEntity WHERE id LIKE concat(?1, '__%%')")
        List<String> findIdsStartingWith(String beginString);

    }
    
}
