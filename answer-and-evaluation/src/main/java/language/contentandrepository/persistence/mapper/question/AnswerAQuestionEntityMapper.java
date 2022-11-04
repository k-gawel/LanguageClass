package language.contentandrepository.persistence.mapper.question;

import language.contentandrepository.persistence.dao.question.AnswerAQuestionJpa;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import lombok.AllArgsConstructor;
import language.contentandrepository.model.domain.question.AnswerAQuestion;
import org.springframework.stereotype.Component;
import language.contentandrepository.persistence.entity.question.AnswerAQuestionEntity;

@Component
@AllArgsConstructor
public class AnswerAQuestionEntityMapper implements EntityToModelMapper<AnswerAQuestionEntity, AnswerAQuestion> {

    private final AnswerAQuestionJpa answerAQuestionJpa;

    @Override
    public AnswerAQuestion fromEntity(AnswerAQuestionEntity entity) {
        return null;
    }

    @Override
    public AnswerAQuestionEntity toEntity(AnswerAQuestion model) {
        var entity = answerAQuestionJpa.findEntityById(model.id().id())
                .orElseGet(AnswerAQuestionEntity::new);

        entity.setId(model.id().id());
        entity.setQuestionContent(model.content());

        return entity;
    }

}
