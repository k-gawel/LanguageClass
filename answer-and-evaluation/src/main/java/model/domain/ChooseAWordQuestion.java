package model.domain;

import java.util.List;

public record ChooseAWordQuestion(
        ID<ChooseAWordQuestion> id,
        List<List<String>> correctAnswers
) implements Question {
}
