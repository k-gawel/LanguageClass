package org.tutor.materials.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.dto.input.Input;

@Service
public class CreatorFacade {

    private final ApplicationContext context;

    @Autowired
    public CreatorFacade(ChapterCreator chapterCreator, ApplicationContext context) {
        this.context = context;
    }

    public <C extends Identifiable> C create(Input<C> input)  {
        return getCreator(input).create(input);
    }

    private <C extends Identifiable, I extends Input<C>> Creator<?, C, I>getCreator(I input) {
        return (Creator<?, C, I>) context
                .getBeansWithAnnotation(IdentifiableCreator.class)
                .values().stream()
                .filter(o -> o.getClass().getAnnotation(IdentifiableCreator.class).equals(input.getClass()))
                .findFirst()
                .orElseThrow();
    }

}
