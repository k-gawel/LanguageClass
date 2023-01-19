package language.graphql.answerandevaluation.input;

import java.util.List;

public record ExerciseAnswerInput(
        String id,
        String exercise,
        String author,
        List<QuestionAnswerInput> questionAnswers,
        List<String> questionAnswerIds
) {
}
