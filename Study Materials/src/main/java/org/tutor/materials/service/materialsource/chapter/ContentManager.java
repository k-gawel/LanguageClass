package org.tutor.materials.service.materialsource.chapter;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.ChapterPart;
import org.tutor.materials.repository.ChapterRepository;

import java.util.Collections;
import java.util.List;

@Service
class ContentManager {

    private final ChapterRepository chapterRepository;

    @Autowired
    ContentManager(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    int addChapterPart(@NotNull Chapter chapter, @NotNull ChapterPart chapterPart, Integer place) {
        var contentList = getContentAndValidate(chapter, chapterPart, false);

        int newIndex;

        if(place == null || place >= contentList.size())
            newIndex = contentList.size();
        else if(place < 1)
            newIndex = 0;
        else
            newIndex = place - 1;

        contentList.add(newIndex, chapterPart);
        setContentAndSave(chapter, contentList);
        return newIndex + 1;
    }

    int moveChapterPart(@NotNull Chapter chapter, @NotNull ChapterPart chapterPart, int newPlace) {
        var contentList = getContentAndValidate(chapter, chapterPart, true);

        var newIndex= move(chapterPart, newPlace, contentList);
        setContentAndSave(chapter, contentList);
        return newIndex + 1;
    }

    private List<ChapterPart> getContentAndValidate(Chapter chapter, ChapterPart part, boolean shouldBelong) {
        var content = chapterRepository.getChapterContent(chapter);
        if((!content.contains(part) && shouldBelong) || (content.contains(part)) && !shouldBelong) {
            throw new IllegalArgumentException(
                    "Chapter part [" + part.getId() + "] "
                    + (shouldBelong ? "doesn't " : "already")
                    + " belongs to chapter [" + chapter.getId() + "]."
            );
        }
        return content;
    }

    private int move(ChapterPart chapterPart, int newPlace, List<ChapterPart> contentList) {
        int newIndex;
        if(newPlace < 1) {
            contentList.remove(chapterPart);
            contentList.add(0, chapterPart);
            newIndex = 1;
        } else if(newPlace >= contentList.size()) {
            contentList.remove(chapterPart);
            contentList.add(chapterPart);
            newIndex = contentList.size();
        } else {
            var currentIndex = contentList.indexOf(chapterPart);
            newIndex = newPlace - 1;
            Collections.swap(contentList, newIndex, currentIndex);
        }
        return newIndex;
    }

    private void setContentAndSave(Chapter chapter, List<ChapterPart> content) {
        chapter.setContent(content);
        chapterRepository.save(chapter);
    }

}
