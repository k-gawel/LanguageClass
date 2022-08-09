package org.tutor.materials.textbook.model.domain.type.content;

import org.tutor.materials.textbook.model.domain.type.ID;

import java.util.List;
import java.util.UUID;

public record ExerciseContent(
        ID<ExerciseContent> id,
        String title,
        ExerciseType type,
        List<UUID> questions
        ) implements ChapterContent {
}
