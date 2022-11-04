package language.contentandrepository.model.domain.question;

import language.contentandrepository.model.DomainID;

import java.util.List;

public record AnswerAQuestion(
        DomainID<AnswerAQuestion> id,
        List<String> content
        ) implements Question{

}
