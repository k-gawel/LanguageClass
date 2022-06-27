package org.tutor.materials.repository;

import org.springframework.stereotype.Repository;
import org.tutor.materials.model.domain.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswerEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContentEntity;
import org.tutor.materials.model.entity.users.StudentEntity;

import java.util.List;

@Repository
public interface ExerciseAnswerRepository extends BasicRepository<ExerciseAnswerEntity, ExerciseAnswer> {

    List<ExerciseAnswerEntity> findExerciseAnswersByExerciseContent(ExerciseContentEntity exerciseContentEntity);

    List<ExerciseAnswerEntity> findExerciseAnswersByExerciseContentAndStudent(ExerciseContentEntity exerciseContentEntity, StudentEntity student);

}
