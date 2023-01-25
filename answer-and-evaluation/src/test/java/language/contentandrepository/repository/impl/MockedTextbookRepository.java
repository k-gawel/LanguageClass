package language.contentandrepository.repository.impl;

import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.repository.textbook.TextbookRepository;
import org.springframework.stereotype.Repository;
import utils.dummyrepositories.DummyContentRepository;
import utils.testmodels.TestTextbook;

import java.util.List;
import java.util.function.Predicate;

@Repository
public class MockedTextbookRepository extends DummyContentRepository<Textbook> implements TextbookRepository {

    public final static List<Textbook> list = List.of(
            TestTextbook.textbookEmptyContent(),
            TestTextbook.textbookEmptyContent(),
            TestTextbook.textbookEmptyContent()
    );

    public MockedTextbookRepository() {
        super(list, Textbook.class);
    }

    @Override
    public List<Textbook> find(Predicate<Textbook> predicate) {
        System.out.println("MockedTextbookRepository.find");
        System.out.println("predicate = " + predicate);
        return super.find(predicate);
    }
}
