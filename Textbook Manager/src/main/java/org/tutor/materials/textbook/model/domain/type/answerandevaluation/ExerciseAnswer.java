package org.tutor.materials.textbook.model.domain.type.answerandevaluation;

import java.util.List;
import java.util.UUID;

public record ExerciseAnswer(
        UUID id,
        UUID ezercise,
        UUID author,
        List<UUID> answers
) {
}
