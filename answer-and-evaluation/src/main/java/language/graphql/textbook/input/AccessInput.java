package language.graphql.textbook.input;

import language.graphql.shared.Input;

public record AccessInput(
        String content,
        String user,
        boolean accessType
) implements Input {
}
