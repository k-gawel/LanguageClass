package language.graphql.textbook.input;

import language.graphql.shared.Input;

public record TextbookAccessInput(
        String teacher,
        String student,
        String textbook,
        boolean access
) implements Input {


}
