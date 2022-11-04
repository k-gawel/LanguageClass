package language.contentandrepository.persistence.mapper.question;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.question.ChooseAWord;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.question.QuestionType;
import language.contentandrepository.persistence.entity.question.ChooseAWordQuestionEntity;
import language.contentandrepository.persistence.entity.question.QuestionContentEntity;

public class QuestionIdMapper {

    public static DomainID<Question> toDomain(QuestionContentEntity.ID entityId) {
        var questionType = QuestionType.fromString(entityId.getType());
        var domainClass = questionType.questionClass();
        var idString = entityId.getId();

        return new DomainID<>(domainClass, idString);
    }

    public static QuestionContentEntity.ID toEntity(DomainID<Question> domainID) {
        var type = domainID.type();

        QuestionContentEntity.ID entity;

        if(type.equals(ChooseAWord.class))
            entity = new ChooseAWordQuestionEntity.ID();
        else
            throw new IllegalArgumentException("Question type " + type + " is not assigned to any entity.");

        entity.setId(domainID.id());
        return entity;
    }

}
