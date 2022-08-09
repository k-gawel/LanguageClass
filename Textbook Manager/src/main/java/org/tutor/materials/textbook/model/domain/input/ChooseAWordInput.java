package org.tutor.materials.textbook.model.domain.input;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record ChooseAWordInput(
        List<String> content,
        List<List<String>> wordChoice,
        List<List<String>> correctAnswers,
        UUID exercise,
        Integer place
)  {

    public boolean validate() {
        var isContentBlank = CollectionUtils.isEmpty(content) || content.stream().allMatch(String::isBlank);
        var contentBlankSpotsCount = content.stream().filter(Objects::isNull).count();
        var isWordChoiceBlank = ValidationUtils.areBlank(wordChoice);
        var areCorrectAnswersBlank = ValidationUtils.areBlank(correctAnswers);
        var isWordChoiceSizeOfBlankSpots = wordChoice.size() == contentBlankSpotsCount;
        var areCorrectAnswersSizeOfBlankSpots = correctAnswers.size() == contentBlankSpotsCount;

        return !isContentBlank &&
                contentBlankSpotsCount > 0 &&
                !isWordChoiceBlank && !areCorrectAnswersBlank &&
                isWordChoiceSizeOfBlankSpots && areCorrectAnswersSizeOfBlankSpots;
    }


}
