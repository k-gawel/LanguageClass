package org.tutor.materials.repository;

import org.springframework.stereotype.Repository;
import org.tutor.materials.model.domain.ExerciseEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswerEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluationEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseEvaluationRepository extends BasicRepository<ExerciseEvaluationEntity, ExerciseEvaluation> {

    Optional<ExerciseEvaluationEntity> findByAnswer(ExerciseAnswerEntity answer);

    List<ExerciseEvaluationEntity> findExerciseEvaluationsByAnswerIn(List<ExerciseAnswerEntity> answers);

}
