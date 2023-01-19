package language.graphql.textbook.input;

import language.graphql.shared.Input;

public record ExampleInput(
        String id,
        String title,
        String content
) implements Input {
}
