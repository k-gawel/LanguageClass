package language.graphql.textbook.input;

import language.graphql.shared.Input;

//if content removing - newPlace should be <0
public record ModifyContentInput(
        String containerId,
        String contentId,
        int newPlace
) implements Input {
}
