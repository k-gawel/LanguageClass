package org.tutor.materials.textbook.service.shared;

import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;

record Input<P extends Domain, C extends Domain>(
        ID<P> parent, ID<C> child, Integer place
) {
}
