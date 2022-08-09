package org.tutor.materials.textbook.model.domain.type.answerandevaluation;

import java.util.List;
import java.util.UUID;

public record QuestionEvaluation(
        UUID id,
        UUID answer,
        UUID author,
        Integer rating,
        List<String> comments
) {
}
