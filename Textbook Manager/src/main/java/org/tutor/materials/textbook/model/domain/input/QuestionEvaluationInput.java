package org.tutor.materials.textbook.model.domain.input;

import java.util.List;
import java.util.UUID;

public record QuestionEvaluationInput(
        UUID answer,
        Integer rating,
        List<String> comments
) {
}
