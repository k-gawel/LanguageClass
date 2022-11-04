package language.contentandrepository.model.domain.question;

import language.contentandrepository.model.DomainID;

import java.util.List;

public record ChooseAWord(
        DomainID<ChooseAWord> id,
        List<String> content,
        List<List<String>> correctAnswers,
        List<List<String>> wordChoice
) implements Question {

}
