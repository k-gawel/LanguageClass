package org.tutor.materials.service.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tutor.materials.textbook.exceptions.ElementAlreadyInsertedException;
import org.tutor.materials.textbook.model.domain.input.TextbookInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;
import org.tutor.materials.textbook.service.managers.interfaces.ChapterManager;
import org.tutor.materials.textbook.service.managers.interfaces.TextbookManager;
import org.tutor.materials.textbook.service.managers.interfaces.UserService;
import org.tutor.materials.textbook.service.repository.interfaces.BasicRepository;
import org.tutor.materials.textbook.service.repository.interfaces.TextbookRepository;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TextbookManagerTest {

    private final TextbookManager manager;
    private final UserService userService;
    private final BasicRepository basicRepository;
    private final TextbookRepository textbookRepository;


    private final ID<? extends AppUser> admin = new ID<>(Teacher.class, "ADMIN");
    private Teacher teacher;

    @Autowired
    TextbookManagerTest(TextbookManager manager, BasicRepository repository, UserService userService, ChapterManager chapterManager, BasicRepository basicRepository, TextbookRepository textbookRepository) {
        this.manager = manager;
        this.userService = userService;
        this.basicRepository = basicRepository;
        this.textbookRepository = textbookRepository;
    }

    @BeforeEach
    public void createTeacher() {
        this.teacher = userService.createTeacher(admin, "Textbook Manager Teacher", "password");
        assertNotNull(this.teacher.id());
    }

    @Test
    public void createPublicTextbookTest() {
        var input = new TextbookInput("Textbook creator test title", teacher.id(), true, Collections.emptyList());
        var textbook = manager.createTextbook(admin, input);

        assertTrue(textbook.id().id().contains("textbook_creator_test_title"));
        assertTrue(textbook.chapters().isEmpty());
    }

    @Test
    public void addChapter() {
        var chapter = basicRepository.findById(new ID<>(Chapter.class, "chapter_test_name")).orElseThrow();
        var textbook = manager.createTextbook(admin, new TextbookInput("title", teacher.id(), true, Collections.emptyList()));
        var chapters = manager.addChapter(admin, textbook.id(), chapter.id(), 0);

        assertEquals(1, chapters.size());
        var fetchedTextbook = textbookRepository.findById(textbook.id()).orElseThrow();

        assertEquals(chapters, fetchedTextbook.chapters());

        assertThrows(ElementAlreadyInsertedException.class, () -> manager.addChapter(admin, textbook.id(), chapter.id(), null));
    }


}