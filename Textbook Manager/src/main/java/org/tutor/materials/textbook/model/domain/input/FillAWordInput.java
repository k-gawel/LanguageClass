package org.tutor.materials.textbook.model.domain.input;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record FillAWordInput(
        List<String> content,
        List<List<String>> correctAnswers,
        UUID exercise,
        Integer place
) {
    public boolean validate() {
        var isContentNonBlank = ValidationUtils.isNonBlank(content);
        var contentBlankSpots = content.stream().filter(Objects::isNull).count();
        var areCorrectAnswersBlank = ValidationUtils.areBlank(correctAnswers);

        return isContentNonBlank &&
                !areCorrectAnswersBlank &&
                contentBlankSpots == correctAnswers.size();
    }
}
