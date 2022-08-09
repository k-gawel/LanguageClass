package org.tutor.materials.textbook.service.repository.interfaces;

import org.springframework.stereotype.Service;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public interface TextbookRepository {

    List<Textbook> findByIds(Collection<ID<Textbook>> ids);

    Optional<Textbook> findById(ID<Textbook> id);

    boolean isPublic(ID<Textbook> textbook);

}
