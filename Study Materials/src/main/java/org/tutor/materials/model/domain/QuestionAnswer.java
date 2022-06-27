package org.tutor.materials.model.domain;

import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.domain.interfaces.QuestionContent;
import org.tutor.materials.model.domain.interfaces.UUID;

import java.util.List;

public record QuestionAnswer(UUID<QuestionAnswer> uuid,
                             UUID<QuestionContent> question,
                             List<String> answers) implements Identifiable {
}
