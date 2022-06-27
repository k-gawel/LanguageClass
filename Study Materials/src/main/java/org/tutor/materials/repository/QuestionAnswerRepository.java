package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.domain.QuestionAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswerEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswerEntity;

import java.util.List;

@Repository
public interface QuestionAnswerRepository extends BasicRepository<QuestionAnswerEntity, QuestionAnswer> {

    @Query("SELECT answer.answers FROM ExerciseAnswerEntity answer WHERE answer = ?1")
    List<QuestionAnswerEntity> findByExerciseAnswer(ExerciseAnswerEntity exerciseAnswerEntity);

}
