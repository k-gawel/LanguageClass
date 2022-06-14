package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.entity.AbstractEntity;

import java.util.NoSuchElementException;

@Repository
public interface BasicRepository<Q extends AbstractEntity> extends JpaRepository<Q, Long> {

    default Q findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new NoSuchElementException("No element with id " + id + " found."));
    };


}
