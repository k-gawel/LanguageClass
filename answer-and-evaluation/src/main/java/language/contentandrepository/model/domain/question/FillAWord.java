package language.contentandrepository.model.domain.question;

import language.contentandrepository.model.DomainID;

import java.util.List;

public record FillAWord(
        DomainID<FillAWord> id,
        List<String> content,
        List<List<String>> correctAnswers
) implements Question {
}
