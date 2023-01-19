package language.service.service.answerandevaluation.services;

import language.contentandrepository.model.domain.answerandevaluation.ExerciseEvaluation;
import language.service.service.answerandevaluation.inputs.ExerciseEvaluationCreateInput;
import language.service.service.answerandevaluation.inputs.ExerciseEvaluationUpdateInput;

public interface ExerciseEvaluationService {

    ExerciseEvaluation create(ExerciseEvaluationCreateInput input);
    ExerciseEvaluation update(ExerciseEvaluationUpdateInput input);

}
