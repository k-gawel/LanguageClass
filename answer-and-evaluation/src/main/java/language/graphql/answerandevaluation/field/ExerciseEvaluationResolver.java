package language.graphql.answerandevaluation.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseEvaluation;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.repository.answerandevalution.ExerciseAnswerRepository;
import language.contentandrepository.repository.answerandevalution.QuestionEvaluationRepository;
import language.contentandrepository.repository.user.TeacherRepository;
import language.graphql.shared.DataFetchingEnvironmentUtils;
import language.graphql.shared.DomainResolver;
import language.graphql.shared.ResolverUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ExerciseEvaluationResolver extends DomainResolver<ExerciseEvaluation> implements GraphQLResolver<ExerciseEvaluation> {

    private final TeacherRepository teacherRepository;
    private final ExerciseAnswerRepository exerciseAnswerRepository;
    private final QuestionEvaluationRepository questionEvaluationRepository;

    @Override
    public String id(ExerciseEvaluation domain) {
        return super.id(domain);
    }

    public Teacher author(ExerciseEvaluation exerciseEvaluation, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                ResolverUtils.getProxy(exerciseEvaluation.author()) :
                teacherRepository.findById(exerciseEvaluation.author()).orElseThrow();
    }

    public ExerciseAnswer answer(ExerciseEvaluation exerciseEvaluation, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                ResolverUtils.getProxy(exerciseEvaluation.answer()) :
                exerciseAnswerRepository.findById(exerciseEvaluation.answer()).orElseThrow();
    }

    public List<QuestionEvaluation> questionEvaluations(ExerciseEvaluation exerciseEvaluation, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                getProxyQuestionEvaluations(exerciseEvaluation) :
                getFullQuestionEvaluations(exerciseEvaluation);
    }

    private List<QuestionEvaluation> getProxyQuestionEvaluations(ExerciseEvaluation exerciseEvaluation) {
        return ResolverUtils.getProxy(exerciseEvaluation.questionEvaluations());
    }

    private List<QuestionEvaluation> getFullQuestionEvaluations(ExerciseEvaluation exerciseEvaluation) {
        return exerciseEvaluation.questionEvaluations().stream()
                .map(questionEvaluationRepository::findById)
                .map(Optional::orElseThrow)
                .toList();
    }


}
