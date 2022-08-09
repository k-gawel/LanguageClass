package org.tutor.materials.textbook.service.repository.interfaces;

import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;

import java.util.Optional;

public interface BasicRepository {

    <Q extends Domain> Optional<Q> findById(ID<Q> id);

}
