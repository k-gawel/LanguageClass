package org.tutor.materials.textbook.model.domain.type.content.question;

import java.util.List;
import java.util.UUID;

public record ChooseAWord(
        UUID id,
        List<String> content,
        List<List<String>> wordChoice
) implements QuestionContent {
}
