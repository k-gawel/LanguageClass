package org.tutor.materials.model.dto.input.chooseaword;

import org.tutor.materials.model.dto.abstracts.QuestionContent;

import java.util.List;

public record ChooseAWordQuestionInput(List<List<String>> correctAnswers,
                                       List<List<String>> wordChoice,
                                       List<String> content) implements QuestionContent {
}
