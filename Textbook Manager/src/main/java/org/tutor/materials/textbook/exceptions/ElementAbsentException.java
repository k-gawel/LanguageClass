package org.tutor.materials.textbook.exceptions;

import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;

public class ElementAbsentException extends AppException {
    public ElementAbsentException(ID<? extends Domain> parent, ID<? extends Domain> child) {
        super(child + " doesn't exists within " + parent + ".");
    }
}
