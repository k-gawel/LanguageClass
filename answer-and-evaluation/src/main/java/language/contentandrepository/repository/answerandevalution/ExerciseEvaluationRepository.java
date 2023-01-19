package language.contentandrepository.repository.answerandevalution;

import language.contentandrepository.criteria.answerandevaluation.ExerciseEvaluationCriteria;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseEvaluation;
import language.contentandrepository.repository.ContentRepository;

import java.util.List;

public interface ExerciseEvaluationRepository extends ContentRepository<ExerciseEvaluation> {

    List<ExerciseEvaluation> find(ExerciseEvaluationCriteria criteria);

}
