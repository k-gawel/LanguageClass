package language.graphql.textbook.input;

import language.graphql.shared.Input;

public record TextbookInput(
        String author,
        String title
) implements Input {
}
