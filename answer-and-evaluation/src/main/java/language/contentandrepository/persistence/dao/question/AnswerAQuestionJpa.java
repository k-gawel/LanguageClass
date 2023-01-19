package language.contentandrepository.persistence.dao.question;

import language.contentandrepository.persistence.dao.IdsJpaRepository;
import org.springframework.data.jpa.repository.Query;
import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.entity.question.AnswerAQuestionEntity;

import java.util.List;
import java.util.Optional;

public interface AnswerAQuestionJpa extends IdentifiableEntityJpaRepository<AnswerAQuestionEntity> {

    @Override
    default Optional<AnswerAQuestionEntity> findEntityById(String id) {
        return findAnswerAQuestionQuestionEntityById(id);
    }

    Optional<AnswerAQuestionEntity> findAnswerAQuestionQuestionEntityById(String id);

    interface ID extends IdsJpaRepository<AnswerAQuestionEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM AnswerAQuestionEntity WHERE id LIKE concat(?1, '__%%')")
        List<String> findIdsStartingWith(String beginString);


    }

}
