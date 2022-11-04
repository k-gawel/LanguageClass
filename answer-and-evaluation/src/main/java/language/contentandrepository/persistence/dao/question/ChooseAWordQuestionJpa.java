package language.contentandrepository.persistence.dao.question;

import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.dao.IdsJpaRepository;
import language.contentandrepository.persistence.entity.question.ChooseAWordQuestionEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChooseAWordQuestionJpa extends IdentifiableEntityJpaRepository<ChooseAWordQuestionEntity> {

    @Override
    default Optional<ChooseAWordQuestionEntity> findEntityById(String id) {
        return findChooseAWordQuestionEntityById(id);
    }

    Optional<ChooseAWordQuestionEntity> findChooseAWordQuestionEntityById(String id);

    interface ID extends IdsJpaRepository<ChooseAWordQuestionEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM ChooseAWordQuestionEntity WHERE id LIKE '#1%%'")
        List<String> findIdsStartingWith(String beginString);

    }
}
