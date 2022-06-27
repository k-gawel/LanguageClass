package org.tutor.materials.service;

import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.dto.input.Input;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IdentifiableCreator {
    Class<? extends Input> value();
}
