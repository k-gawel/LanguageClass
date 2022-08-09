package org.tutor.materials.textbook.service.managers.interfaces;

import org.tutor.materials.textbook.model.domain.input.ExerciseContentInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.ExerciseContent;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;

public interface ExerciseManager {

    ExerciseContent createExercise(ID<? extends Teacher> accessor, ExerciseContentInput input);


}
