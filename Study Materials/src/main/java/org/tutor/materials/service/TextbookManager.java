package org.tutor.materials.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.domain.Textbook;
import org.tutor.materials.model.dto.input.TextbookInput;
import org.tutor.materials.model.entity.materialsource.textbook.TextbookEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.ChapterEntity;
import org.tutor.materials.repository.ChapterRepository;
import org.tutor.materials.repository.TextbookRepository;

@Service
public class TextbookManager extends OrderedContentContainerManager<TextbookEntity, ChapterEntity> {

    private final TextbookRepository repository;
    private final ChapterRepository chapterRepository;

    @Autowired
    public TextbookManager(TextbookRepository repository, ChapterRepository chapterRepository) {
        this.repository = repository;
        this.chapterRepository = chapterRepository;
    }

    public Textbook saveTextbook(TextbookInput input) {
        var entity = fromInput(input);
        repository.save(entity);
        return fromEntity(entity);
    }

    TextbookEntity fromInput(TextbookInput input) {
        var entity = new TextbookEntity();
        entity.setName(input.name());
        return entity;
    }

    Textbook fromEntity(TextbookEntity entity) {
        return new Textbook(
                entity.getUUID(),
                entity.getName()
        );
    }

}
