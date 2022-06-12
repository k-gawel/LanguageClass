package org.tutor.materials.service.materialsource.chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.dto.input.ChapterInput;
import org.tutor.materials.model.entity.materialsource.textbook.Textbook;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.repository.ChapterRepository;
import org.tutor.materials.service.materialsource.TextbookService;

@Service
class ChapterCreator {

    private final ChapterRepository chapterRepository;
    private final TextbookService textbookService;

    @Autowired
    ChapterCreator(ChapterRepository chapterRepository, TextbookService textbookService) {
        this.chapterRepository = chapterRepository;
        this.textbookService = textbookService;
    }

    Chapter createChapter(Textbook textbook, ChapterInput input) {
        var chapter = fromInput(input);
        textbookService.addChapter(textbook, chapter, input.number());
        return chapter;
    }

    private Chapter fromInput(ChapterInput input) {
        var chapter = new Chapter();
        chapter.setTitle(input.title());
        return chapterRepository.save(chapter);
    }

}
