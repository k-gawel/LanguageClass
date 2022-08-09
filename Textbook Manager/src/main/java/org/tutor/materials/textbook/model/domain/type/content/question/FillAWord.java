package org.tutor.materials.textbook.model.domain.type.content.question;

import java.util.List;
import java.util.UUID;

public record FillAWord(
        UUID id,
        List<String> content
) implements QuestionContent {
}
