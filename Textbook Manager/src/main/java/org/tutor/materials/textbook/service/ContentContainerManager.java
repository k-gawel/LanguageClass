package org.tutor.materials.textbook.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tutor.materials.textbook.model.entity.shared.AbstractEntity;
import org.tutor.materials.textbook.model.entity.shared.OrderedContentContainerEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public abstract class ContentContainerManager<E extends OrderedContentContainerEntity<C>, C extends AbstractEntity> {

    protected final JpaRepository<E, UUID> parentRepository;
    protected final JpaRepository<C, UUID> childRepository;

    protected ContentContainerManager(JpaRepository<E, UUID> parentRepository, JpaRepository<C, UUID> childRepository) {
        this.parentRepository = parentRepository;
        this.childRepository = childRepository;
    }


    @Transactional
    public Integer addContent(UUID parentID, UUID childId, Integer place) {
        var parent = parentRepository.findById(parentID).orElseThrow();
        var child = childRepository.findById(childId).orElseThrow();

        var content = parent.getContentIds();
        var actualPlace = add(content, childId, place);
        parentRepository.save(parent);
        return actualPlace;
    }

    Integer add(List contentList, Object child, Integer place) {
        contentList.remove(child);
        if(place == null || place > contentList.size()) {
            contentList.add(child);
            return contentList.size();
        } else if(place <= 1) {
            contentList.add(0, child);
            return 1;
        } else {
            contentList.add(place - 1, child);
            return place;
        }
    }

}
