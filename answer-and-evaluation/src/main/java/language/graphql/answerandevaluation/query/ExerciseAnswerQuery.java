package language.graphql.answerandevaluation.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import language.contentandrepository.criteria.answerandevaluation.ExerciseAnswerCriteria;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.repository.answerandevalution.ExerciseAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExerciseAnswerQuery implements GraphQLQueryResolver {

    private final ExerciseAnswerRepository exerciseAnswerRepository;

    @PostAuthorize("@answerAndEvaluationFilter.hasAccess(authentication, returnObject)")
    public ExerciseAnswer getExerciseAnswer(String id) {
        return exerciseAnswerRepository.getById(id);
    }

    @PostAuthorize("@answerAndEvaluationFilter.hasAccess(authentication, returnObject)")
    public List<ExerciseAnswer> findExerciseAnswers(ExerciseAnswerCriteria criteria) {
        return null;
    }

}
