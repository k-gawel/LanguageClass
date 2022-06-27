package org.tutor.materials.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.domain.interfaces.UUID;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.OrderedContentContainerEntity;
import org.tutor.materials.repository.question.DefaultOrderedContentContainerRepository;

import java.util.List;

@Service
public class OrderedContentContainerManager<OWNER_ENTITY extends OrderedContentContainerEntity<CHILD_ENTITY>,
                                              CHILD_ENTITY extends AbstractEntity> {

    @Autowired
    private DefaultOrderedContentContainerRepository<OWNER_ENTITY, CHILD_ENTITY> repository;


    public int addContentPart(UUID<? extends Identifiable> owner, UUID<? extends Identifiable> child, Integer place) {
        var ownerEntity = repository.findById(owner).orElseThrow();
        var childEntity = repository.findContentEntity(child).orElseThrow();
        var childrenList = repository.findContent(owner);

        int actualPlace = addToContent(childrenList, childEntity, place);
        ownerEntity.setContent(childrenList);
        repository.save(ownerEntity);
        return actualPlace;
    }

    <C extends AbstractEntity> int addToContent(@NotNull List<C> content, @NotNull C newItem, @Nullable Integer place) {
        if(place == null || place > content.size()) {
            content.add(newItem);
            return content.size();
        } else if(place <= 1) {
            content.add(0, newItem);
            return 1;
        } else {
            content.add(place - 1, newItem);
            return place;
        }
    }




}
