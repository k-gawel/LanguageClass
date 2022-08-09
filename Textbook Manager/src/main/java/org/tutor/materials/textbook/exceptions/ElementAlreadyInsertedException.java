package org.tutor.materials.textbook.exceptions;

import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;

public class ElementAlreadyInsertedException extends AppException {
    public ElementAlreadyInsertedException(ID<? extends Domain> parentId, ID<? extends Domain> childId) {
        super(childId + " already exists within " + parentId + ".");
    }
}
