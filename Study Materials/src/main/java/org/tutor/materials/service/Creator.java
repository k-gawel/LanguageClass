package org.tutor.materials.service;

import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.dto.input.Input;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.repository.BasicRepository;


public abstract class Creator<E extends AbstractEntity, D extends Identifiable, I extends Input>  {

    private final BasicRepository<E, D> repository;

    protected Creator(BasicRepository<E, D> repository) {
        this.repository = repository;
    }

    public D create(I input) {
        var entity = fromInput(input);
        repository.save(entity);
        return fromEntity(entity);
    }

    protected abstract D fromEntity(E entity);
    protected abstract E fromInput(I input);

}
