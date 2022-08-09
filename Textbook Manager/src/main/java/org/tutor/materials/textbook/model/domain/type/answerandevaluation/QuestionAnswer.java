package org.tutor.materials.textbook.model.domain.type.answerandevaluation;

import java.util.List;
import java.util.UUID;

public record QuestionAnswer(
        UUID id,
        UUID question,
        List<String> answers
) {
}
