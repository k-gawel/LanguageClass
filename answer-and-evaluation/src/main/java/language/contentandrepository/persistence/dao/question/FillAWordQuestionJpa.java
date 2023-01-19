package language.contentandrepository.persistence.dao.question;

import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.dao.IdsJpaRepository;
import language.contentandrepository.persistence.entity.question.FillAWordQuestionEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FillAWordQuestionJpa extends IdentifiableEntityJpaRepository<FillAWordQuestionEntity> {


    @Override
    default Optional<FillAWordQuestionEntity> findEntityById(String id) {
        return findFillAWordQuestionEntityById(id);
    }

    Optional<FillAWordQuestionEntity> findFillAWordQuestionEntityById(String id);

    interface ID extends IdsJpaRepository<FillAWordQuestionEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM FillAWordQuestionEntity WHERE id LIKE concat(?1, '__%%')")
        List<String> findIdsStartingWith(String beginString);

    }

}
