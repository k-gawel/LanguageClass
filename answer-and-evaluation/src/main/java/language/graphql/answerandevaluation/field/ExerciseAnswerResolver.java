package language.graphql.answerandevaluation.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import language.graphql.shared.ResolverUtils;
import language.graphql.shared.DataFetchingEnvironmentUtils;
import language.graphql.shared.DomainResolver;
import graphql.schema.DataFetchingEnvironment;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.repository.impl.textbook.BaseExerciseRepository;
import language.contentandrepository.repository.impl.BaseStudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import language.contentandrepository.repository.impl.answerandevaluation.BaseQuestionAnswerRepository;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ExerciseAnswerResolver extends DomainResolver<ExerciseAnswer> implements GraphQLResolver<ExerciseAnswer> {

    private final BaseExerciseRepository exerciseRepository;
    private final BaseStudentRepository studentRepository;
    private final BaseQuestionAnswerRepository questionAnswerRepository;

    @Override
    public String id(ExerciseAnswer domain) {
        return super.id(domain);
    }

    public Exercise exercise(ExerciseAnswer exerciseAnswer, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                ResolverUtils.getProxy(exerciseAnswer.exercise()) : exerciseRepository.findById(exerciseAnswer.exercise()).orElseThrow();
    }

    public Student author(ExerciseAnswer exerciseAnswer, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                ResolverUtils.getProxy(exerciseAnswer.author()) : studentRepository.findById(exerciseAnswer.author()).orElseThrow();
    }

    public List<QuestionAnswer> answers(ExerciseAnswer exerciseAnswer, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                getProxyAnswers(exerciseAnswer) : getFullAnswers(exerciseAnswer);
    }

    private List<QuestionAnswer> getProxyAnswers(ExerciseAnswer exerciseAnswer) {
        return ResolverUtils.getProxy(exerciseAnswer.answers());
    }

    private List<QuestionAnswer> getFullAnswers(ExerciseAnswer exerciseAnswer) {
        return exerciseAnswer.answers().stream()
                .map(questionAnswerRepository::findById)
                .map(Optional::orElseThrow)
                .toList();
    }

}
