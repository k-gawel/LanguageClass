package language.graphql.question.input;

import language.graphql.shared.Input;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record FillAWordInput(
        String author,
        List<String> content,
        List<List<String>> correctAnswers
) implements Input {

    private Optional<String> validateContent() {
        if(Objects.isNull(content))
            return Optional.of("Content must be present.");

        var hasAtLeastOneNullPart = content.stream().anyMatch(Objects::isNull);
        var hasAtLeastOneNonNullPart = content.stream().anyMatch(Objects::nonNull);

        if(!hasAtLeastOneNonNullPart)
            return Optional.of("Content must contains at least one non-null part.");
        else if(!hasAtLeastOneNullPart)
            return Optional.of("Content must contains at least one null part.");
        else
            return Optional.empty();
    }

    private Optional<String> validateCorrectAnswers() {
        if(CollectionUtils.isEmpty(correctAnswers))
            return Optional.of("Correct answers must not be empty.");

        var emptyPlaces = content.stream().filter(Objects::isNull).count();

        if(correctAnswers.size() != emptyPlaces)
            return Optional.of("Correct answers size must be equal empty spaces in content.");
        else if(correctAnswers.stream().anyMatch(List::isEmpty))
            return Optional.of("Correct answers must not be empty.");
        else
            return Optional.empty();
    }

}
