package org.tutor.materials.model.dto.input.chooseaword;

import org.springframework.util.CollectionUtils;
import org.tutor.materials.model.dto.input.QuestionInput;
import org.tutor.materials.model.questiontype.ChooseAWord;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Objects;

public record ChooseAWordQuestionInput(List<List<String>> correctAnswers,
                                       List<List<String>> wordChoice,
                                       List<String> content) implements QuestionInput<ChooseAWord> {

    @Override
    public Class<ChooseAWord> type() {
        return ChooseAWord.class;
    }

    public void validate() throws ValidationException {
        if(CollectionUtils.isEmpty(content))
            throw new ValidationException("Content can't be empty.");
        if(CollectionUtils.isEmpty(wordChoice))
            throw new ValidationException("Word choice can't be empty.");
        if(CollectionUtils.isEmpty(correctAnswers))
            throw new ValidationException("Correct answers can't be empty.");

        var blankPlaces = content.stream().filter(Objects::isNull).count();

        if(blankPlaces == 0)
            throw new ValidationException("No blank spaces found!");
        if(correctAnswers.size() != blankPlaces)
            throw new ValidationException("Incorrect correct answers size.");
        if(wordChoice.size() != blankPlaces)
            throw new ValidationException("Incorrect word choice size.");

    }

}
