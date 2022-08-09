package org.tutor.materials.textbook.model.domain.input;

import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;

public record ContentModificationInput<PARENT extends Domain, CHILD extends Domain>(
        ID<PARENT> parent,
        ID<CHILD> child,
        Integer place,
        ContentModificationAction action
) {
}
