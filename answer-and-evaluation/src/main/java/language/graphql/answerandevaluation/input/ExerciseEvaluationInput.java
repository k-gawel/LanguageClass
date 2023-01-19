package language.graphql.answerandevaluation.input;

import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.Teacher;

import java.util.List;

public record ExerciseEvaluationInput(
        DomainID<ExerciseAnswer> answer,
        DomainID<Teacher> author,
        List<DomainID<QuestionEvaluation>> questionEvaluations,
        String comment,
        int rating
) {
}
