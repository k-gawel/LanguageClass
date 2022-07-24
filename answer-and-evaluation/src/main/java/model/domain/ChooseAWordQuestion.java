package model.domain;

import java.util.List;

public record ChooseAWordQuestion(
        ID<ChooseAWordQuestion> id,
        List<String> content,
        List<List<String>> wordsChoice,
        List<List<String>> correctAnswers
) implements Question {
}
