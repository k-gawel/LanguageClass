package org.tutor.materials.textbook.service.repository.interfaces;

import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ChapterRepository {

    List<Chapter> findByIds(Collection<ID<Chapter>> ids);

    Optional<Chapter> findById(ID<Chapter> id);

}
