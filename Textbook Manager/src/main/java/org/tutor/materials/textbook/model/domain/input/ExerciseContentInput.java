package org.tutor.materials.textbook.model.domain.input;

import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.content.ExerciseType;

public record ExerciseContentInput(
        String title,
        ExerciseType type,
        ID<Chapter> chapter,
        Integer place
) implements ChapterPartInput {
}
