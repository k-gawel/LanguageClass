package language.graphql.answerandevaluation.input;

import java.util.List;

public record QuestionEvaluationInput(
        String answer,
        String author,
        List<String> comments,
        int score
) {


}
