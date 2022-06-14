package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.QuestionContent;

import java.util.List;

@Repository
public interface ExerciseContentRepository extends BasicRepository<ExerciseContent> {

    @Query("SELECT questions FROM ExerciseContent exercise WHERE exercise = ?1")
    List<QuestionContent> getQuestions(ExerciseContent exerciseContent);

}
