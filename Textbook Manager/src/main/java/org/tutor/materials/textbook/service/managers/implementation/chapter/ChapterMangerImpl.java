package org.tutor.materials.textbook.service.managers.implementation.chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.textbook.model.domain.input.ChapterInput;
import org.tutor.materials.textbook.model.domain.input.ContentModificationAction;
import org.tutor.materials.textbook.model.domain.input.ContentModificationInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.content.ChapterContent;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;
import org.tutor.materials.textbook.service.events.events.ChapterContentEvent;
import org.tutor.materials.textbook.service.events.events.ChapterCreationEvent;
import org.tutor.materials.textbook.service.events.processors.PublishEvent;
import org.tutor.materials.textbook.service.managers.interfaces.ChapterManager;
import org.tutor.materials.textbook.service.managers.interfaces.TextbookManager;
import org.tutor.materials.textbook.service.shared.ContentManager;

import java.util.List;

@Service
public class ChapterMangerImpl implements ChapterManager {

    private final ChapterCreator chapterCreator;
    private final ContentManager<Chapter, ChapterContent> contentManager;

    private final TextbookManager textbookManager;

    @Autowired
    public ChapterMangerImpl(ChapterCreator chapterCreator, ContentManager<Chapter, ChapterContent> contentManager, TextbookManager textbookManager) {
        this.chapterCreator = chapterCreator;
        this.contentManager = contentManager;
        this.textbookManager = textbookManager;
    }

    @Override
    @PublishEvent(ChapterCreationEvent.class)
    public Chapter create(ID<Teacher> accessor, ChapterInput input) {
        var chapter = chapterCreator.create(input.title());
        textbookManager.addChapter(accessor, input.textbook(), chapter.id(), input.place());
        return chapter;
    }

    @Override
    public List<ID<ChapterContent>> addContent(ID<Teacher> accessor, ID<Chapter> chapter, ID<ChapterContent> item, Integer place) {
        var input = new ContentModificationInput<>(chapter, item, place, ContentModificationAction.ADD);
        return modifyContent(accessor, input);
    }

    @Override
    public List<ID<ChapterContent>> moveContent(ID<Teacher> accessor, ID<Chapter> chapter, ID<ChapterContent> item, Integer place) {
        var input = new ContentModificationInput<>(chapter, item, place, ContentModificationAction.MOVE);
        return modifyContent(accessor, input);
    }

    @Override
    public List<ID<ChapterContent>> removeContent(ID<Teacher> accessor, ID<Chapter> chapter, ID<ChapterContent> content) {
        var input = new ContentModificationInput(chapter, chapter, null, ContentModificationAction.REMOVE);
        return modifyContent(accessor, input);
    }

    @PublishEvent(ChapterContentEvent.class)
    private List<ID<ChapterContent>> modifyContent(ID<? extends AppUser> accessor, ContentModificationInput<Chapter, ChapterContent> input) {
        return switch (input.action()) {
            case ADD -> contentManager.add(input.parent(), input.child(), input.place());
            case MOVE -> contentManager.move(input.parent(), input.child(), input.place());
            case REMOVE -> contentManager.remove(input.parent(), input.child());
        };
    }

}
