package org.tutor.materials.model.dto.output;

import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluation;

public record ExerciseAnswerAndEvaluation(ExerciseAnswer answer, ExerciseEvaluation evaluation) {
}
