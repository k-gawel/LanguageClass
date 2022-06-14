package org.tutor.materials.resolver.mutation;

import org.tutor.materials.model.dto.input.chooseaword.ChooseAWordExerciseInput;
import org.tutor.materials.model.dto.input.chooseaword.ChooseAWordQuestionInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.repository.BasicRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChooseAWordContentMutationTest {

    @Autowired
    ChooseAWordQuestionResolver resolver;
    @Autowired
    BasicRepository<Chapter> repository;

    @Test
    public void commonChoiceQuestion() {
        var chapter = new Chapter();
        chapter.setTitle("Chapter title");
        repository.save(chapter);

        var input = new ChooseAWordExerciseInput(
                "title",
                1L,
                1,
                List.of("ik", "jij"),
                List.of(new ChooseAWordQuestionInput(
                        List.of(List.of("jij"), List.of("ik")),
                        null,
                        Arrays.stream(new String[] {null, "bent niet ", null}).toList()
                ))
        );

        resolver.createChooseAWordExercise("token", chapter.getId(), input);
    }

}
