package org.tutor.materials.textbook.service.managers.interfaces;

import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;

import java.util.List;

public interface ContentManager<P extends Domain, C extends Domain> {

    List<ID<C>> addContent(ID<Teacher> accessor, ID<P> parent, ID<C> child, Integer place);

    List<ID<C>> moveContent(ID<Teacher> accessor, ID<P> parent, ID<C> child, Integer place);

    List<ID<C>> removeContent(ID<Teacher> accessor, ID<P> parent, ID<C> child);

}
