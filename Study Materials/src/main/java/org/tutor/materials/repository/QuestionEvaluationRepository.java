package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.QuestionEvaluation;

import java.util.List;

@Repository
public interface QuestionEvaluationRepository extends BasicRepository<QuestionEvaluation> {

    @Query("SELECT evaluation FROM QuestionEvaluation evaluation WHERE evaluation.answer in ?1")
    List<QuestionEvaluation> findByAnswers(List<QuestionAnswer> answers);

    @Query("SELECT evaluation FROM QuestionEvaluation evaluation WHERE evaluation.answer = ?1")
    List<QuestionEvaluation> findByExerciseAnswer(ExerciseAnswer answer);

    @Query("SELECT evaluation.questionEvaluations FROM ExerciseEvaluation evaluation WHERE evaluation = ?1")
    List<QuestionEvaluation> findByExerciseEvaluation(ExerciseEvaluation evaluation);
}
