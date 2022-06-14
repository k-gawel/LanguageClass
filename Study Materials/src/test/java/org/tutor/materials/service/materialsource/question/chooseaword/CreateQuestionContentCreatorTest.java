package org.tutor.materials.service.materialsource.question.chooseaword;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.tutor.materials.model.dto.input.chooseaword.ChooseAWordQuestionInput;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.repository.BasicRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

@RunWith(MockitoJUnitRunner.class)
class CreateQuestionContentCreatorTest {

    @Mock
    private BasicRepository<AbstractEntity> repository;

    @InjectMocks
    private QuestionContentCreator service;

    private final ObjectMapper mapper = new ObjectMapper();

    private long idSequence = 0;

    @BeforeEach
    public void setMock() {
        MockitoAnnotations.openMocks(this);
        doAnswer(invocation -> {
            var entity = invocation.getArgument(0);
            ReflectionTestUtils.setField(entity, "id", idSequence++);
            return entity;
        }).when(repository).save(any(AbstractEntity.class));
        assertNotNull(repository);
    }

    @Test
    public void test() {
        var entity = new AbstractEntity() {

        };
        var entity2 = repository.save(entity);
        System.out.println(entity2);
    }

    @Test
    public void properQuestionWithoutCommonChoice() throws JsonProcessingException {

        @Language("JSON")
        String inputJSON = """
                {
                  "correctAnswers": [["hebben"]],
                  "content": [null, " jullie al pauze?"],
                  "choiceOfWords": [["hebben", "hebt"]]
                }""";

        var input = mapper.readValue(inputJSON, ChooseAWordQuestionInput.class);

        var question = service.createQuestion(input, null);

        assertNotNull(question.getId());

        var expectedCorrectAnswers = List.of(List.of("hebben"));
        var actualCorrectAnswers = question.getCorrectAnswers();
        assertArrayEquals(expectedCorrectAnswers.toArray(), actualCorrectAnswers.toArray());

        assertEquals(input.content().size(), question.getContentParts().size());
        assertEquals(input.wordChoice().size(), question.getWordChoice().size());
        assertEquals(input.correctAnswers().size(), question.getCorrectAnswers().size());
    }

    @Test
    public void properQuestionsWithCommonChoice() throws JsonProcessingException {
        @Language("JSON")
        String question1InputJson = """
                {
                  "correctAnswers": [["hij"]],
                  "content": ["Dit is Mustafa, mijn buurman. ", null, " woont in de Emmastraat."]
                }""";

        @Language("JSON")
        String question2InputJson = """
                {
                "correctAnswers": [["jij"], ["jij", "je"]],
                "content": ["Ning komt uit China. En ", null, ", Yin? Kom ", null, " ook uit China?"]
                }""";

        var input1 = mapper.readValue(question1InputJson, ChooseAWordQuestionInput.class);
        var input2 = mapper.readValue(question2InputJson, ChooseAWordQuestionInput.class);


        var commonChoice = List.of("hij", "jij", "je", "ik", "u");


        var question1 = service.createQuestion(input1, commonChoice);
        var question2 = service.createQuestion(input2, commonChoice);

        assertNotNull(question1.getId());
        assertEquals(1, question1.getWordChoice().size());
        assertEquals(commonChoice, question1.getWordChoice().get(0));

        assertNotNull(question2.getId());
        assertEquals(2, question2.getWordChoice().size());
        assertEquals(commonChoice, question2.getWordChoice().get(0));
        assertEquals(commonChoice, question2.getWordChoice().get(1));
    }

    @Test
    public void noWordChoicesProvided() throws JsonProcessingException {
        @Language("JSON")
        String inputJSON = """
                {
                "correctAnswers": [["jij"], ["jij", "je"]],
                "content": ["Ning komt uit China. En ", null, ", Yin? Kom ", null, " ook uit China?"]
                }""";

        var input = mapper.readValue(inputJSON, ChooseAWordQuestionInput.class);

        assertThrows(IllegalArgumentException.class, () -> {
            service.createQuestion(input, null);
        });
    }



}