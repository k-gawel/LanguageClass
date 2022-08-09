package org.tutor.materials.textbook.model.domain.type.answerandevaluation;

import java.util.List;
import java.util.UUID;

public record ExerciseEvaluation(
        UUID id,
        UUID author,
        UUID answer,
        Integer rating,
        String comment,
        List<UUID> evaluations
) {
}
