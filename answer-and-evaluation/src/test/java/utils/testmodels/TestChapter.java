package utils.testmodels;

import language.contentandrepository.model.domain.textbook.Chapter;

import java.util.Collections;

public class TestChapter {

    public static Chapter emptyChapter() {

        return new Chapter(
                TestModel.generateId(Chapter.class),
                "chapter_title",
                Collections.emptyList()
        );

    }

}
