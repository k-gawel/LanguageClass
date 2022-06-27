package org.tutor.materials.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tutor.materials.model.dto.input.TextbookInput;
import org.tutor.materials.model.entity.materialsource.textbook.TextbookEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.ChapterEntity;
import org.tutor.materials.repository.ChapterRepository;
import org.tutor.materials.repository.TextbookRepository;
import org.tutor.materials.repository.question.DefaultOrderedContentContainerRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TextbookServiceTest_saveTextbook {

    @Autowired
    TextbookManager textbookService;
    @Autowired
    ChapterCreator chapterService;
    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    DefaultOrderedContentContainerRepository<TextbookEntity, ChapterEntity> containerRepository;
    @Autowired
    TextbookRepository repository;

    @Test
    public void test() {
        // given
        var input = new TextbookInput("New textbook.");
        // when
        var actual = textbookService.saveTextbook(input);
        //then
        Assertions.assertNotNull(actual.uuid());
        Assertions.assertEquals(actual.name(), input.name());
        Assertions.assertNotNull(actual.chapters());
        Assertions.assertTrue(actual.chapters().isEmpty());

    }

}