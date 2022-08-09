package org.tutor.materials.textbook.model.domain.input;

import java.util.UUID;

public record ExerciseEvaluationInput(
        UUID answer,
        Integer rating,
        String comment
) {
}
