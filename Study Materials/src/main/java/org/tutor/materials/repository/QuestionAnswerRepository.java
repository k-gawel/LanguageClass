package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswer;

import java.util.List;

@Repository
public interface QuestionAnswerRepository extends BasicRepository<QuestionAnswer> {

    @Query("SELECT answer.answers FROM ExerciseAnswer answer WHERE answer = ?1")
    List<QuestionAnswer> findByExerciseAnswer(ExerciseAnswer exerciseAnswer);

}
