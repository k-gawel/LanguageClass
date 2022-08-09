package org.tutor.materials.textbook.service.shared;

import org.apache.logging.log4j.util.TriConsumer;
import org.tutor.materials.textbook.exceptions.ElementAbsentException;
import org.tutor.materials.textbook.exceptions.ElementAlreadyInsertedException;
import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;

import java.util.List;

interface FailOn<P extends Domain,  C extends Domain> extends TriConsumer<ID<P>, List<String>, ID<C>> {

    static void onPresent(ID<? extends Domain> parentId, List<String> contentIds, ID<? extends Domain> childId) {
        if(contentIds.contains(childId.id()))
            throw new ElementAlreadyInsertedException(parentId, childId);
    }

    static void onAbsent(ID<? extends Domain> parentId, List<String> contentIds, ID<? extends Domain> childId) {
        if(!contentIds.contains(childId.id()))
            throw new ElementAbsentException(parentId, childId);
    }

}
