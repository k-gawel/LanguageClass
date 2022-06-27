package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.entity.AbstractEntity;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BasicRepository<E extends AbstractEntity, O extends Identifiable> extends JpaRepository<E, UUID> {
//
//    Optional<E> findByUUID(String uuid);
//
//    Optional<E> findByUUID(UUID uuid);
//
//    Optional<E> findByUUID(org.tutor.materials.model.domain.interfaces.UUID<O> uuid);

}
