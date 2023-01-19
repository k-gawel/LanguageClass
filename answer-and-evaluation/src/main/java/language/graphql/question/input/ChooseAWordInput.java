package language.graphql.question.input;

import language.graphql.shared.Input;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public record ChooseAWordInput(
        String author,
        List<String> content,
        List<List<String>> wordChoice,
        List<List<String>> correctAnswers
) implements Input {

    static class Validator implements ConstraintValidator<Valid, ChooseAWordInput> {

        @Override
        public void initialize(Valid constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(ChooseAWordInput input, ConstraintValidatorContext constraintValidatorContext) {
            return Stream.of(
                    validateContent(input),
                    validateWordChoice(input),
                    validateCorrectAnswers(input)
            )
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .peek(m -> {
                        constraintValidatorContext.disableDefaultConstraintViolation();
                        constraintValidatorContext.buildConstraintViolationWithTemplate(m).addConstraintViolation();
                    })
                    .findAny()
                    .isEmpty();
        }

        private Optional<String> validateContent(ChooseAWordInput input) {
            if(Objects.isNull(input.content()))
                return Optional.of("Content must be present.");

            var hasAtLeastOneNullPart = input.content().stream().anyMatch(Objects::isNull);
            var hasAtLeastOneNonNullPart = input.content().stream().anyMatch(Objects::nonNull);

            if(!hasAtLeastOneNonNullPart)
                return Optional.of("Content must contains at least one non-null part.");
            else if(!hasAtLeastOneNullPart)
                return Optional.of("Content must contains at least one null part.");
            else
                return Optional.empty();
        }

        private Optional<String> validateWordChoice(ChooseAWordInput input) {
            var nullParts = input.content().stream().filter(Objects::isNull).count();

            if(Objects.isNull(input.wordChoice()))
                return Optional.of("Word choice must be present.");
            else if(input.wordChoice().size() != nullParts)
                return Optional.of("Word choice size must match null parts of content.");
            else if(input.wordChoice().stream().anyMatch(CollectionUtils::isEmpty))
                return Optional.of("Each part of word choice must be present.");
            else
                return Optional.empty();
        }

        private Optional<String> validateCorrectAnswers(ChooseAWordInput input) {
            var nullParts = input.content().stream().filter(Objects::isNull).count();

            if(Objects.isNull(input.correctAnswers()))
                return Optional.of("Correct answers must be present.");
            else if(input.correctAnswers().size() != nullParts)
                return Optional.of("Correct answers size must match null parts of content.");
            else if(input.correctAnswers().stream().anyMatch(CollectionUtils::isEmpty))
                return Optional.of("Each part of correct answers must be present.");
            else if(doesWordChoiceContainsCorrectAnswers(input))
                return Optional.of("Correct answers must be included in word choice.");
            else
                return Optional.empty();
        }

        private boolean doesWordChoiceContainsCorrectAnswers(ChooseAWordInput input) {
            for (int i = 0; i < input.wordChoice().size(); i++) {
                if(!input.wordChoice().get(i).containsAll(input.correctAnswers().get(i)))
                    return false;
            }
            return true;
        }

    }




}
