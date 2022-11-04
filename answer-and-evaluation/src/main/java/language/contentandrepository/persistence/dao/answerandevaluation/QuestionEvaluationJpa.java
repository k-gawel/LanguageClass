package language.contentandrepository.persistence.dao.answerandevaluation;

import language.contentandrepository.persistence.dao.IdsJpaRepository;
import org.springframework.data.jpa.repository.Query;
import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.entity.answerandevaluation.QuestionEvaluationEntity;

import java.util.List;
import java.util.Optional;

public interface QuestionEvaluationJpa extends IdentifiableEntityJpaRepository<QuestionEvaluationEntity> {

    @Override
    default Optional<QuestionEvaluationEntity> findEntityById(String id) {
        return findQuestionEvaluationEntityById(id);
    }

    Optional<QuestionEvaluationEntity> findQuestionEvaluationEntityById(String id);

    interface ID extends IdsJpaRepository<QuestionEvaluationEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM QuestionEvaluationEntity WHERE id LIKE '#1%%'")
        List<String> findIdsStartingWith(String beginString);

    }
    
}
