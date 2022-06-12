package org.tutor.materials.model.dto.output;

import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluation;

public record ExerciseContentAnswerEvaluation(ExerciseContent content,
                                              ExerciseAnswer answer,
                                              ExerciseEvaluation evaluation) {
}
