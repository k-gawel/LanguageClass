package utils.testmodels;

import language.contentandrepository.model.domain.textbook.Textbook;

import java.util.ArrayList;

public final class TestTextbook {

    public static Textbook textbookEmptyContent() {
        return new Textbook(
                TestModel.generateId(Textbook.class),
                "title",
                new ArrayList<>(),
                TestTeacher.generate().id(),
                TestClock.INSTANCE().now()
        );
    }

    public static Textbook textbookWithContent() {
        return textbookWithContent(5);
    }

    public static Textbook textbookWithContent(int contentSize) {
        var textbook = textbookEmptyContent();
        for (int i = 0; i < contentSize; i++) {
            textbook.chapters().add(TestChapter.emptyChapter().id());
        }
        return textbook;
    }

}
