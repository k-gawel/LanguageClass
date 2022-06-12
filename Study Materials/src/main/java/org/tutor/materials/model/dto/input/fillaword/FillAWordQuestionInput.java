package org.tutor.materials.model.dto.input.fillaword;

import java.util.List;

public record FillAWordQuestionInput(List<String> content,
                                     List<List<String>> correctAnswers) {
}
