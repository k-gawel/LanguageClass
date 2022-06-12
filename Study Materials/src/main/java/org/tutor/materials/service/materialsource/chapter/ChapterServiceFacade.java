package org.tutor.materials.service.materialsource.chapter;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.dto.input.ChapterInput;
import org.tutor.materials.model.entity.materialsource.textbook.Textbook;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.ChapterPart;
import org.tutor.materials.repository.ChapterRepository;
import org.tutor.materials.repository.TextbookRepository;
import org.tutor.materials.service.materialsource.TextbookService;

@Service
class ChapterServiceFacade implements ChapterService {

    private final ChapterCreator creator;
    private final ContentManager contentManager;

    private final ChapterRepository chapterRepository;

    @Autowired
    public ChapterServiceFacade(ChapterCreator creator, ContentManager contentManager, ChapterRepository repository, TextbookService textbookService, TextbookRepository textbookRepository) {
        this.creator = creator;
        this.contentManager = contentManager;
        this.chapterRepository = repository;
    }

    @Override
    public Chapter createChapter(@NotNull Textbook textbook, ChapterInput chapterInput) {
        return creator.createChapter(textbook, chapterInput);
    }

    @Override
    public int addChapterPart(Chapter chapter, ChapterPart chapterPart, Integer place) {
        return contentManager.addChapterPart(chapter, chapterPart, place);
    }

    @Override
    public int moveChapterPart(Chapter chapter, ChapterPart chapterPart, int newPlace) {
        return contentManager.moveChapterPart(chapter, chapterPart, newPlace);
    }

}
