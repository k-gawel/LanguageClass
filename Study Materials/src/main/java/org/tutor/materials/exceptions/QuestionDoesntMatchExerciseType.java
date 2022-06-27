package org.tutor.materials.exceptions;

import org.jetbrains.annotations.NotNull;
import org.tutor.materials.model.domain.ExerciseContent;
import org.tutor.materials.model.domain.interfaces.QuestionContent;

public class QuestionDoesntMatchExerciseType extends Exception {

    public QuestionDoesntMatchExerciseType(@NotNull ExerciseContent exercise, @NotNull QuestionContent question) {
        super("Expected " + exercise.type().getSimpleName() + ". Got: " + question.type().getSimpleName());
    }

}
