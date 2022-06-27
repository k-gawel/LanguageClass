package org.tutor.materials.model.dto.output;

import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswerEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluationEntity;

public record ExerciseAnswerAndEvaluation(ExerciseAnswerEntity answer, ExerciseEvaluationEntity evaluation) {
}
