package language.contentandrepository.persistence.dao.question;

import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.dao.IdsJpaRepository;
import org.springframework.data.jpa.repository.Query;
import language.contentandrepository.persistence.entity.question.QuestionContentEntity;

import java.util.List;
import java.util.Optional;

public interface QuestionContentJpa extends IdentifiableEntityJpaRepository<QuestionContentEntity> {

    @Override
    default Optional<QuestionContentEntity> findEntityById(String id) {
        return findQuestionContentEntityById(id);
    }

    Optional<QuestionContentEntity> findQuestionContentEntityById(String id);

    interface ID extends IdsJpaRepository<QuestionContentEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM QuestionContentEntity  WHERE id LIKE concat(?1, '__%%')")
        List<String> findIdsStartingWith(String beginString);

    }

}