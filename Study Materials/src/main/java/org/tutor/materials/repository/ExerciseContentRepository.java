package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.domain.ExerciseContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContentEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.QuestionContentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExerciseContentRepository extends BasicRepository<ExerciseContentEntity, ExerciseContent> {

    @Query("SELECT exercise.content FROM ExerciseContentEntity exercise WHERE exercise = ?1")
    List<QuestionContentEntity> getQuestions(ExerciseContentEntity exerciseContentEntity);

    @Query("SELECT c.content FROM ExerciseContentEntity c WHERE c.UUID = ?1")
    List<QuestionContentEntity> findContentList(UUID uuid);

    @Query("SELECT q FROM QuestionContentEntity q WHERE q.UUID = ?1")
    Optional<QuestionContentEntity> findContentEntity(UUID uuid);
}
