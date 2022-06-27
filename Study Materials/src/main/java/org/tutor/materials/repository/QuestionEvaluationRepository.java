package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.domain.QuestionEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswerEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluationEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswerEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.QuestionEvaluationEntity;

import java.util.List;

@Repository
public interface QuestionEvaluationRepository extends BasicRepository<QuestionEvaluationEntity, QuestionEvaluation> {

    @Query("SELECT evaluation FROM QuestionEvaluationEntity evaluation WHERE evaluation.answer in ?1")
    List<QuestionEvaluationEntity> findByAnswers(List<QuestionAnswerEntity> answers);

    @Query("SELECT evaluation FROM QuestionEvaluationEntity evaluation WHERE evaluation.answer = ?1")
    List<QuestionEvaluationEntity> findByExerciseAnswer(ExerciseAnswerEntity answer);

    @Query("SELECT evaluation.questionEvaluations FROM ExerciseEvaluationEntity evaluation WHERE evaluation = ?1")
    List<QuestionEvaluationEntity> findByExerciseEvaluation(ExerciseEvaluationEntity evaluation);
}
