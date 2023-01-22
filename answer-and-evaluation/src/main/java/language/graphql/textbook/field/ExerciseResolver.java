package language.graphql.textbook.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.repository.question.QuestionRepository;
import language.graphql.shared.DataFetchingEnvironmentUtils;
import language.graphql.shared.DomainResolver;
import language.graphql.shared.ResolverUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ExerciseResolver extends DomainResolver<Exercise> implements GraphQLResolver<Exercise> {

    private final QuestionRepository questionRepository;

    @Override
    public String id(Exercise domain) {
        return super.id(domain);
    }

    public List<Question> questions(Exercise exercise, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                getProxyQuestions(exercise) : getFullQuestions(exercise);
    }

    private List<Question> getProxyQuestions(Exercise exercise) {
        return ResolverUtils.getProxy(exercise.questions());
    }

    private List<Question> getFullQuestions(Exercise exercise) {
        return exercise.questions().stream()
                .map(this::getQuestion)
                .toList();
    }

    private Question getQuestion(DomainID<Question> questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException(ObjectUtils.toString(questionId)));
    }

}
