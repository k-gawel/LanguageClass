package language.graphql.answerandevaluation.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.repository.question.QuestionRepository;
import language.contentandrepository.repository.user.StudentRepository;
import language.graphql.shared.DataFetchingEnvironmentUtils;
import language.graphql.shared.DomainResolver;
import language.graphql.shared.ResolverUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuestionAnswerResolver extends DomainResolver<QuestionAnswer> implements GraphQLResolver<QuestionAnswer> {

    private final QuestionRepository questionRepository;
    private final StudentRepository studentRepository;

    @Override
    public String id(QuestionAnswer domain) {
        return super.id(domain);
    }

    public Question question(QuestionAnswer questionAnswer, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                ResolverUtils.getProxy(questionAnswer.question()) : questionRepository.findById(questionAnswer.question()).orElseThrow();
    }

    public Student student(QuestionAnswer questionAnswer, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                ResolverUtils.getProxy(questionAnswer.student()) : studentRepository.findById(questionAnswer.student()).orElseThrow();
    }


}
