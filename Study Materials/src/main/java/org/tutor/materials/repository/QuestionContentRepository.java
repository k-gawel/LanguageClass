package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.domain.interfaces.QuestionContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContentEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswerEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.QuestionContentEntity;

import java.util.List;

@Repository
public interface QuestionContentRepository extends BasicRepository<QuestionContentEntity, QuestionContent> {


    @Query("SELECT size(e.content) FROM ExerciseContentEntity e WHERE e = ?1")
    long getQuestionsSize(ExerciseContentEntity exerciseContentEntity);

    @Query("SELECT answer.question FROM QuestionAnswerEntity answer WHERE answer = ?1")
    QuestionContentEntity getByAnswer(QuestionAnswerEntity answer);

    @Query("SELECT e.content FROM ExerciseContentEntity e WHERE e = ?1")
    List<QuestionContentEntity> getByExercise(ExerciseContentEntity e);

}
