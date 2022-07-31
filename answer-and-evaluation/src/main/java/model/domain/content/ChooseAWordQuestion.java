package model.domain.content;

import model.domain.ID;

import java.util.List;

public record ChooseAWordQuestion(
        ID<ChooseAWordQuestion> id,
        List<List<String>> correctAnswers
) implements Question {
}
