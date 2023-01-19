package language.contentandrepository.repository.answerandevalution;

import language.contentandrepository.criteria.answerandevaluation.ExerciseAnswerCriteria;
import language.contentandrepository.criteria.answerandevaluation.ExerciseEvaluationCriteria;
import language.contentandrepository.criteria.answerandevaluation.QuestionAnswerCriteria;
import language.contentandrepository.criteria.answerandevaluation.QuestionEvaluationCriteria;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseEvaluation;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;

import java.util.List;

public interface AnswerAndEvaluationRepository {

    List<ExerciseAnswer> find(ExerciseAnswerCriteria criteria);
    List<QuestionAnswer> find(QuestionAnswerCriteria criteria);
    List<ExerciseEvaluation> find(ExerciseEvaluationCriteria criteria);
    List<QuestionEvaluation> find(QuestionEvaluationCriteria criteria);

}
