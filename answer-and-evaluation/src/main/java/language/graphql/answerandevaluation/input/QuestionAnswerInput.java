package language.graphql.answerandevaluation.input;

import java.util.List;

public record QuestionAnswerInput(
        String id,
        String question,
        String author,
        List<String> answers
) {
}
