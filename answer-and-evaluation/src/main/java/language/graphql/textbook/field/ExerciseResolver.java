package language.graphql.textbook.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import language.graphql.shared.ResolverUtils;
import language.graphql.shared.DataFetchingEnvironmentUtils;
import language.graphql.shared.DomainResolver;
import graphql.schema.DataFetchingEnvironment;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.textbook.Exercise;
import lombok.AllArgsConstructor;
import language.contentandrepository.model.DomainID;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import language.contentandrepository.repository.impl.answerandevaluation.BaseQuestionRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class ExerciseResolver extends DomainResolver<Exercise> implements GraphQLResolver<Exercise> {

    private final BaseQuestionRepository questionRepository;

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
