package org.tutor.materials.service.materialsource.chapter;

import org.tutor.materials.model.dto.input.ChapterInput;
import org.tutor.materials.model.entity.materialsource.textbook.Textbook;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.ChapterPart;

public interface ChapterService {

    Chapter createChapter(Textbook textbook, ChapterInput chapterInput);



    /**
     * @param chapter
     * @param chapterPart
     * @param place place in chapter parts list on which new chapter part must be inserted
     * @return place number to which chapter part actually was assigned.
     */
    int addChapterPart(Chapter chapter, ChapterPart chapterPart, Integer place);

    /**
     * @param chapter target chapter
     * @param chapterPart target ChapterPart
     * @param newPlace place number to which chapterPart must be reassigned
     * @return place number to which chapter part was actually moved
     * @throws java.util.NoSuchElementException if chapter part is not found in chapter
     */
    int moveChapterPart(Chapter chapter, ChapterPart chapterPart, int newPlace);

}
