package org.tutor.materials.service.repository.implementations;

import io.vavr.Tuple2;
import org.junit.jupiter.api.Test;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.content.ChapterContent;
import org.tutor.materials.textbook.service.repository.implementations.ChapterRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChapterRepositoryImplTest {

    @Test
    public void createChapter_emptyContentList() {
        var idAndTitle = new Tuple2<>("id", "title");
        var stringContentIds = new ArrayList<String>();

        var repository = new ChapterRepositoryImpl();

        var chapter = repository.createChapter(idAndTitle, stringContentIds);

        assertEquals(new ID<>(Chapter.class, "id"), chapter.id());
        assertEquals("title", chapter.title());
        assertTrue(chapter.content().isEmpty());
    }

    @Test
    public void createChapter_nonEmptyContentList() {
        var idAndTitle = new Tuple2<>("id", "title");
        var stringContentIds = List.of("content1", "content2", "content3");

        var repository = new ChapterRepositoryImpl();

        var chapter = repository.createChapter(idAndTitle, stringContentIds);

        assertEquals(new ID<>(Chapter.class, "id"), chapter.id());
        assertEquals("title", chapter.title());
        assertEquals(3, chapter.content().size());
        assertEquals(new ID<>(ChapterContent.class, "content1"), chapter.content().get(0));
        assertEquals(new ID<>(ChapterContent.class, "content2"), chapter.content().get(1));
        assertEquals(new ID<>(ChapterContent.class, "content3"), chapter.content().get(2));

    }

}