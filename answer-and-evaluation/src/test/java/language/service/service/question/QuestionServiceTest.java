package language.service.service.question;

import utils.dummyrepositories.TestChooseAWordQuestionRepositoryFactory;
import utils.dummyrepositories.TestFillAWordQuestionRepositoryFactory;
import language.contentandrepository.model.domain.question.ChooseAWord;
import language.contentandrepository.repository.question.ChooseAWordQuestionRepository;
import language.contentandrepository.repository.question.FillAWordQuestionRepository;
import language.graphql.question.input.ChooseAWordInput;
import language.service.service.question.implementations.QuestionService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class QuestionServiceTest {

    private final ChooseAWordQuestionRepository chooseAWordQuestionRepository = TestChooseAWordQuestionRepositoryFactory.emptyRepository();
    private final FillAWordQuestionRepository fillAWordQuestionRepository = TestFillAWordQuestionRepositoryFactory.emptyRepository();
    private final QuestionService questionService = new QuestionService(chooseAWordQuestionRepository, fillAWordQuestionRepository);

    @Test
    public void createChooseAWordTest() {
        var content = new ArrayList<String>();
        content.add("choose a");
        content.add(null);
        content.add("question");

        var input = new ChooseAWordInput(
                "teacher_1",
                content,
                List.of(List.of("word", "sentence")),
                List.of(List.of("word"))
        );

        var generatedId = chooseAWordQuestionRepository.generateId("choose a word");

        assertTrue(chooseAWordQuestionRepository.findById(generatedId).isEmpty());

        var result = questionService.create(input);

        var expected =                 new ChooseAWord(
                generatedId,
                input.content(),
                input.correctAnswers(),
                input.wordChoice()
        );

        assertEquals(expected, result);
        assertEquals(expected, chooseAWordQuestionRepository.getById(generatedId));
    }

}