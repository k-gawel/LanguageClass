package language.contentandrepository.persistence.mapper.question;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.question.FillAWord;
import language.contentandrepository.persistence.dao.question.FillAWordQuestionJpa;
import language.contentandrepository.persistence.entity.question.FillAWordQuestionEntity;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FillAWordQuestionEntityMapper implements EntityToModelMapper<FillAWordQuestionEntity, FillAWord> {

    private final FillAWordQuestionJpa repository;

    @Override
    public FillAWord fromEntity(FillAWordQuestionEntity entity) {
        return new FillAWord(
                new DomainID<>(FillAWord.class, entity.getId()),
                entity.getContentParts(),
                entity.getCorrectAnswers()
        );
    }

    @Override
    public FillAWordQuestionEntity toEntity(FillAWord model) {
        var entity = repository.findEntityById(model.id().id())
                .orElseGet(FillAWordQuestionEntity::new);

        entity.setId(model.id().id());
        entity.setContentParts(model.content());
        entity.setCorrectAnswers(model.correctAnswers());

        return entity;
    }

}
