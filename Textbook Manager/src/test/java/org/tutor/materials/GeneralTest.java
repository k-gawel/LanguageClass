package org.tutor.materials;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tutor.materials.textbook.model.domain.input.ChapterInput;
import org.tutor.materials.textbook.model.domain.input.TextbookInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;
import org.tutor.materials.textbook.service.managers.interfaces.ChapterManager;
import org.tutor.materials.textbook.service.managers.interfaces.TextbookManager;
import org.tutor.materials.textbook.service.managers.interfaces.UserService;
import org.tutor.materials.textbook.service.repository.interfaces.TextbookRepository;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GeneralTest {

    @Autowired
    UserService userService;
    @Autowired
    TextbookManager textbookManager;
    @Autowired
    ChapterManager chapterManager;
    @Autowired
    TextbookRepository textbookRepository;


    private final ID<Teacher> adminId = new ID<>(Teacher.class, "ADMIN");

    @Test
    public void test() {
        var teacher = userService.createTeacher(adminId, "Teacher", "TeacherPassword");
        var textbook = textbookManager.createTextbook(teacher.id(),
                new TextbookInput("New Textbook", teacher.id(), true, Collections.emptyList()));
        var chapter1 = chapterManager.create(teacher.id(),
                new ChapterInput(textbook.id(), "Chapter Title 1 ", null));
        System.out.println("Chapter 1:\n" + chapter1);
        var chapter2 = chapterManager.create(teacher.id(),
                new ChapterInput(textbook.id(), "Chapter Title 2", null));
        System.out.println("Chapter 2:\n" + chapter2);
        var chapter3 = chapterManager.create(teacher.id(),
                new ChapterInput(textbook.id(), "Chapter Title 2", null));
        System.out.println("Chapter 3:\n" + chapter3);
        textbookManager.moveChapter(teacher.id(), textbook.id(), chapter1.id(), 3);
        var chapters = textbookRepository.findById(textbook.id()).orElseThrow().chapters();
        assertEquals(chapter2, chapters.get(0));
        assertEquals(chapter3, chapters.get(1));
        assertEquals(chapter1, chapters.get(2));
    }


}
