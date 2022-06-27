package org.tutor.materials.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.ChapterEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContentEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionType;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
class ChapterRepositoryTest {

    @Autowired private ChapterRepository repository;
    @Autowired private ExerciseContentRepository exerciseRepository;

    @Test
    public void getContentIds() {
        //given
        var chapter = repository.save(new ChapterEntity("Test chapter title."));
        var chapterParts = List.of(
                        new ExerciseContentEntity(QuestionType.ANSWER_A_QUESTION, "Exercise title 1"),
                        new ExerciseContentEntity(QuestionType.ANSWER_A_QUESTION, "Exercise title 2"))
                .stream().map(exerciseRepository::save).toList();
        chapter.getContent().addAll(chapterParts);
        repository.save(chapter);
        //when
        var ids = chapterParts.stream().map(AbstractEntity::getUUID).map(UUID::toString).toList();
        var actualIds = repository.getContentIds(chapter.getUUID());
        //then
        assertEquals(ids.size(), actualIds.size());
        assertTrue(ids.containsAll(actualIds));

    }

}