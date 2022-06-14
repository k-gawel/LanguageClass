package org.tutor.materials.service.materialsource.question.chooseaword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.dto.input.chooseaword.ChooseAWordQuestionInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.ChooseAWordContent;
import org.tutor.materials.repository.QuestionContentRepository;

import java.util.List;
import java.util.Objects;

@Service
class QuestionContentCreator {

    private final QuestionContentRepository repository;


    @Autowired
    public QuestionContentCreator(QuestionContentRepository repository) {
        this.repository = repository;
    }

    ChooseAWordContent createQuestion(ChooseAWordQuestionInput input, List<String> commonChoice) {
        var question = new ChooseAWordContent();

        question.setContentParts(input.content());
        setChoiceOfWords(question, input, commonChoice);
        setCorrectAnswers(question, input);

        return repository.save(question);
    }

    private void setCorrectAnswers(ChooseAWordContent question, ChooseAWordQuestionInput input) {
        var blankSpacesSize = blankSpacesSize(input);

        if(blankSpacesSize != input.correctAnswers().size()) {
            throw new IllegalArgumentException("Correct answers size doesn't match number of blank spaces.");
        }

        question.setCorrectAnswers(input.correctAnswers());
    }

    private void setChoiceOfWords(ChooseAWordContent question, ChooseAWordQuestionInput input, List<String> commonChoice) {
        List<List<String>> choices = createWordChoice(input, commonChoice);
        question.setWordChoice(choices);
    }

    private List<List<String>> createWordChoice(ChooseAWordQuestionInput input, List<String> commonChoice) {
        if(commonChoice != null) {
            return createChoicesFromCommonChoice(input, commonChoice);
        } else if(input.wordChoice() != null) {
            return createChoicesFromIndividualChoices(input);
        } else {
            throw new IllegalArgumentException("Word choices are missing.");
        }
    }

    private List<List<String>> createChoicesFromCommonChoice(ChooseAWordQuestionInput input, List<String> commonChoice) {
        return input.content().stream().filter(Objects::isNull)
                .map((e) -> commonChoice)
                .toList();
    }

    private List<List<String>> createChoicesFromIndividualChoices(ChooseAWordQuestionInput input) {
        var blankSpacesSize = blankSpacesSize(input);

        if (blankSpacesSize != input.wordChoice().size())
            throw new IllegalArgumentException("Choice of words size doesn't match number of empty spaces.");

        return input.wordChoice();
    }

    private long blankSpacesSize(ChooseAWordQuestionInput input) {
        return input.content().stream().filter(Objects::isNull).count();
    }

}
