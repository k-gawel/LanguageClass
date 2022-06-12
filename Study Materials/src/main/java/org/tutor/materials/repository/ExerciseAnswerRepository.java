package org.tutor.materials.repository;

import org.springframework.stereotype.Repository;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.model.entity.users.Student;

import java.util.List;

@Repository
public interface ExerciseAnswerRepository extends BasicRepository<ExerciseAnswer> {

    List<ExerciseAnswer> findExerciseAnswersByExerciseContent(ExerciseContent exerciseContent);

    List<ExerciseAnswer> findExerciseAnswersByExerciseContentAndStudent(ExerciseContent exerciseContent, Student student);

}
