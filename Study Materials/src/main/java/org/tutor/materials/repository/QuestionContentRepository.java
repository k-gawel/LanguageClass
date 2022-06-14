package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.QuestionContent;

import java.util.List;

@Repository
public interface QuestionContentRepository extends BasicRepository<QuestionContent> {


    @Query("SELECT size(e.questions) FROM ExerciseContent e WHERE e = ?1")
    long getQuestionsSize(ExerciseContent exerciseContent);

    @Query("SELECT answer.question FROM QuestionAnswer answer WHERE answer = ?1")
    QuestionContent getByAnswer(QuestionAnswer answer);

    @Query("SELECT e.questions FROM ExerciseContent e WHERE e = ?1")
    List<QuestionContent> getByExercise(ExerciseContent e);

}
