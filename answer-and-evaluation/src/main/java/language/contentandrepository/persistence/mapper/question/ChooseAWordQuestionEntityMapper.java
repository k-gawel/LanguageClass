package language.contentandrepository.persistence.mapper.question;

import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import language.contentandrepository.model.domain.question.ChooseAWord;
import lombok.AllArgsConstructor;
import language.contentandrepository.model.DomainID;
import org.springframework.stereotype.Component;
import language.contentandrepository.persistence.dao.question.ChooseAWordQuestionJpa;
import language.contentandrepository.persistence.entity.question.ChooseAWordQuestionEntity;

@Component
@AllArgsConstructor
public class ChooseAWordQuestionEntityMapper implements EntityToModelMapper<ChooseAWordQuestionEntity, ChooseAWord> {

    private final ChooseAWordQuestionJpa chooseAWordQuestionJpa;

    @Override
    public ChooseAWord fromEntity(ChooseAWordQuestionEntity entity) {
        return new ChooseAWord(
                new DomainID<>(ChooseAWord.class, entity.getId()),
                entity.getContentParts(),
                entity.getCorrectAnswers(),
                entity.getWordChoice()
        );
    }

    @Override
    public ChooseAWordQuestionEntity toEntity(ChooseAWord model) {
        var entity = chooseAWordQuestionJpa.findEntityById(model.id().id())
                .orElseGet(ChooseAWordQuestionEntity::new);

        entity.setId(model.id().id());
        entity.setWordChoice(model.wordChoice());
        entity.setContentParts(model.content());
        entity.setCorrectAnswers(model.correctAnswers());

        return entity;
    }

}
