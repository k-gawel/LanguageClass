package org.tutor.materials.model.domain;

import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.domain.interfaces.UUID;

public record ExerciseEvaluation(UUID<ExerciseEvaluation> uuid,
                                 ExerciseAnswer answer,
                                 Integer overallScore,
                                 String comment) implements Identifiable {
}
