package org.tutor.materials.repository;

import org.springframework.stereotype.Repository;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluation;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseEvaluationRepository extends BasicRepository<ExerciseEvaluation> {

    Optional<ExerciseEvaluation> findByAnswer(ExerciseAnswer answer);

    List<ExerciseEvaluation> findExerciseEvaluationsByAnswerIn(List<ExerciseAnswer> answers);

}
