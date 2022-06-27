package org.tutor.materials.model.domain;

import org.tutor.materials.model.domain.interfaces.ContentContainer;
import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.domain.interfaces.UUID;

import java.util.List;

public record ExerciseAnswer(UUID<ExerciseAnswer> uuid,
                             ExerciseContent exercise,
                             List<UUID<QuestionAnswer>> answers) implements Identifiable, ContentContainer<QuestionAnswer> {

    @Override
    public List<UUID<QuestionAnswer>> content() {
        return answers;
    }

}
