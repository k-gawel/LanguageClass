package org.tutor.materials.service.managers.implementation.chapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;
import org.tutor.materials.textbook.service.managers.implementation.chapter.ChapterCreator;
import org.tutor.materials.textbook.service.managers.interfaces.UserService;
import org.tutor.materials.textbook.service.repository.interfaces.ChapterRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChapterCreatorTest {

    @Autowired
    private ChapterCreator chapterCreator;
    @Autowired
    private UserService userService;
    @Autowired
    private ChapterRepository chapterRepository;

    private final ID<? extends AppUser> adminId = new ID<>(Teacher.class, "ADMIN");
    private Teacher teacher;

    @BeforeEach
    public void setTeacher() {
        this.teacher = userService.createTeacher(adminId, "Teacher Chapter Creator Test", "password");
    }

    @Test
    void create() {
        var title = "Chapter Test Name";
        var chapter = chapterCreator.create(title);

        assertTrue(chapter.id().id().contains("chapter_test_name"));
        assertEquals(title, chapter.title());
        assertTrue(chapter.content().isEmpty());

        var chapterFromRepository = chapterRepository.findById(chapter.id());
        assertEquals(chapterFromRepository.orElseThrow(), chapter);
    }

}