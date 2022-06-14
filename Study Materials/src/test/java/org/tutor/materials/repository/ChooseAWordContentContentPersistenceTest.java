package org.tutor.materials.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.ChooseAWordContent;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class ChooseAWordContentContentPersistenceTest {

    @Autowired
    QuestionContentRepository repository;

    @Test
    public void individualChoices() {
        var question = new ChooseAWordContent();

        question.getContentParts().add(null);
        question.getContentParts().add(" jullie al pauze?");

        question.getCorrectAnswers().add(List.of("hebben"));

        question.getWordChoice().add(List.of("hebben", "hebt"));

        repository.save(question);

        assertNotNull(question.getId());
    }

    @Test
    public void commonChoiceForOneQuestion() {
        var question = new ChooseAWordContent();

        question.getContentParts().add("Ning komt uit China. En ");
        question.getContentParts().add(null);
        question.getContentParts().add(", Yin? Kom ");
        question.getContentParts().add(null);
        question.getContentParts().add(" ook uit China?");

        question.getCorrectAnswers().add(List.of("jij"));
        question.getCorrectAnswers().add(List.of("jij", "je"));

        var commonChoice = List.of("jij", "je", "hij", "ze", "zji");

        question.getWordChoice().add(commonChoice);
        question.getWordChoice().add(commonChoice);

        repository.save(question);
    }

    @Test
    public void commonChoiceForMultipleQuestions() {
        var commonChoice = List.of("je", "jullie", "hij", "jij");

        var question1 = new ChooseAWordContent();

        question1.getContentParts().add("Sonja, kun ");
        question1.getContentParts().add(null);
        question1.getContentParts().add(" je achternaam spelen?");

        question1.getCorrectAnswers().add(List.of("je"));

        question1.getWordChoice().add(commonChoice);

        var question2 = new ChooseAWordContent();

        question2.getContentParts().add("Juha en Artio, komen ");
        question2.getContentParts().add(null);
        question2.getContentParts().add(" uit Finland?");

        question2.getCorrectAnswers().add(List.of("jullie"));

        question2.getWordChoice().add(commonChoice);

        repository.save(question1);
        repository.save(question2);
    }


}