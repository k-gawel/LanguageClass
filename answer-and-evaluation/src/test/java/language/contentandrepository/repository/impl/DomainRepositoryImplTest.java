package language.contentandrepository.repository.impl;

import language.LanguageApiTest;
import language.contentandrepository.criteria.textbook.TextbookCriteria;
import language.contentandrepository.model.domain.textbook.Textbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LanguageApiTest.class)
public class DomainRepositoryImplTest {


    @Autowired
    private DomainRepositoryImpl repository;

    @Test
    public void test() {
        var textbooks = IntStream.range(0, 2).mapToObj(MockedTextbookRepository.list::get).toList();
        System.out.println("textbooks = " + textbooks);

        var ids = textbooks.stream().map(Textbook::id).toList();
        var criteria = TextbookCriteria.builder().ids(ids).build();

        var result = repository.find(criteria);

        assertEquals(textbooks, result);
    }

}