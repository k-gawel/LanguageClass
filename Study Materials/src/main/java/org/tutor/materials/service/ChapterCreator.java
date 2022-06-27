package org.tutor.materials.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.domain.Chapter;
import org.tutor.materials.model.domain.interfaces.UUID;
import org.tutor.materials.model.dto.input.ChapterInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.ChapterEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.ChapterPartEntity;
import org.tutor.materials.repository.ChapterRepository;

import java.util.ArrayList;

@Service
@IdentifiableCreator(value = ChapterInput.class)
public class ChapterCreator extends Creator<ChapterEntity, Chapter, ChapterInput> {

    private final ChapterRepository repository;

    @Autowired
    public ChapterCreator(ChapterRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public ChapterEntity fromInput(ChapterInput input) {
        var entity = new ChapterEntity();
        entity.setTitle(input.title());
        return entity;
    }

    @Override
    public Chapter fromEntity(ChapterEntity entity) {
        return new Chapter(
                new UUID<Chapter>(entity.getUUID(), Chapter.class),
                entity.getTitle(),
                new ArrayList<>()
        );
    }

}
