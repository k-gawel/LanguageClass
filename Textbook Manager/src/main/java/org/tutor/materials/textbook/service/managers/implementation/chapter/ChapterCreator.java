package org.tutor.materials.textbook.service.managers.implementation.chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.entity.content.ChapterEntity;
import org.tutor.materials.textbook.service.DAO;
import org.tutor.materials.textbook.service.managers.implementation.Creator;
import org.tutor.materials.textbook.service.managers.interfaces.TextbookManager;

import java.util.Collections;

@Service
class ChapterCreator extends Creator {

    private final DAO dao;

    @Autowired
    ChapterCreator(DAO dao, TextbookManager textbookManager) {
        super(dao);
        this.dao = dao;
    }

    Chapter create(String title) {
        var entity = createEntity(title);
        var chapter = fromEntity(entity);
        return chapter;
    };

    private ChapterEntity createEntity(String title) {
        var entity = new ChapterEntity();
        entity.setTitle(title);
        saveNewEntity(entity);
        return entity;
    }

    private Chapter fromEntity(ChapterEntity e) {
        return new Chapter(
                new ID<>(Chapter.class, e.getId()),
                e.getTitle(),
                Collections.emptyList()
        );
    }

}
