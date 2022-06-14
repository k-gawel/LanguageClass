package org.tutor.materials.service.materialsource.question;

import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluation;

public interface ClosedEvaluationCreator {

    ExerciseEvaluation evaluate(ExerciseAnswer answer);

}
