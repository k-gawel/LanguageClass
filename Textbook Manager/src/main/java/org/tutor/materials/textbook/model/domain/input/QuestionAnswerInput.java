package org.tutor.materials.textbook.model.domain.input;

import java.util.List;
import java.util.UUID;

public record QuestionAnswerInput(
        UUID id,
        List<String> answers
) {
}
