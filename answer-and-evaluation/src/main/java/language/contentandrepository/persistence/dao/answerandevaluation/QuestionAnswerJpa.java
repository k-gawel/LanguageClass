package language.contentandrepository.persistence.dao.answerandevaluation;

import language.contentandrepository.persistence.dao.IdsJpaRepository;
import org.springframework.data.jpa.repository.Query;
import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.entity.answerandevaluation.QuestionAnswerEntity;

import java.util.List;
import java.util.Optional;

public interface QuestionAnswerJpa extends IdentifiableEntityJpaRepository<QuestionAnswerEntity> {

    @Override
    default Optional<QuestionAnswerEntity> findEntityById(String id) {
        return findQuestionAnswerEntityById(id);
    }

    Optional<QuestionAnswerEntity> findQuestionAnswerEntityById(String id);

    interface ID extends IdsJpaRepository<QuestionAnswerEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM QuestionAnswerEntity WHERE id LIKE '#1%%'")
        List<String> findIdsStartingWith(String beginString);

    }
    
}
