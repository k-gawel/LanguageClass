package org.tutor.materials.model.domain;

import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.domain.interfaces.UUID;

import java.util.List;

public record QuestionEvaluation(UUID<QuestionEvaluation> uuid,
                                 QuestionAnswer answer,
                                 List<String> comments,
                                 List<Boolean> areAnswersCorrect) implements Identifiable {
}
