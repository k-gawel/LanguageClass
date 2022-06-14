package serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.tutor.materials.model.dto.input.chooseaword.ChooseAWordQuestionInput;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddChooseAWordContentRequestSerializationTest {

    @Test
    public void test() throws IOException {
        var choiceOfWords = new ArrayList<List<String>>();
        var choice1 = new ArrayList<String>();
        choice1.add("hebben");
        choice1.add("hebt");
        choiceOfWords.add(choice1);

        var correctAnswers = new ArrayList<List<String>>();
        var answers1 = new ArrayList<String>();
        answers1.add("hebben");
        correctAnswers.add(answers1);

        var content = new ArrayList<String>();
        content.add(null);
        content.add(" jullie al pauze?");

        var expected = new ChooseAWordQuestionInput(correctAnswers, choiceOfWords, content);

        var path = "src/test/resources/choose_a_word.json";
        var mapper = new ObjectMapper();
        var actual = mapper.readValue(Paths.get(path).toFile(), ChooseAWordQuestionInput.class);

        assertEquals(expected, actual);
    }

}
