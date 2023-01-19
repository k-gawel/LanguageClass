package language.service.service.answerandevaluation.services;

import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.service.service.answerandevaluation.inputs.QuestionEvaluationCreateInput;
import language.service.service.answerandevaluation.inputs.QuestionEvaluationUpdateInput;

public interface QuestionEvaluationService {

    QuestionEvaluation createQuestionEvaluation(QuestionEvaluationCreateInput input);
    QuestionEvaluation updateQuestionEvaluation(QuestionEvaluationUpdateInput input);

}
