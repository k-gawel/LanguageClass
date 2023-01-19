package language.contentandrepository.repository.answerandevalution;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.repository.ContentRepository;

import java.util.List;

public interface QuestionEvaluationRepository extends ContentRepository<QuestionEvaluation> {

    List<QuestionEvaluation> getEvaluationsByAnswer(DomainID<QuestionAnswer> id);

}
