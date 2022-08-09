package org.tutor.materials.textbook.exceptions;

import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;

public class NotFoundException extends AppException {

    public NotFoundException(String id, Class<? extends Domain> resourceType) {
        super(String.format("%1$s with id '%2$s' not found.", resourceType.getSimpleName(), id));
    }

    public NotFoundException(ID<? extends Domain> id) {
        this(id.id(), id.type());
    }

}
