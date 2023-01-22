package language.graphql.answerandevaluation.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.repository.answerandevalution.QuestionAnswerRepository;
import language.contentandrepository.repository.user.TeacherRepository;
import language.graphql.shared.DataFetchingEnvironmentUtils;
import language.graphql.shared.DomainResolver;
import language.graphql.shared.ResolverUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuestionEvaluationResolver extends DomainResolver<QuestionEvaluation> implements GraphQLResolver<QuestionEvaluation> {

    private final QuestionAnswerRepository questionAnswerRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public String id(QuestionEvaluation domain) {
        return super.id(domain);
    }

    public QuestionAnswer answer(QuestionEvaluation questionEvaluation, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                ResolverUtils.getProxy(questionEvaluation.answer()) : questionAnswerRepository.findById(questionEvaluation.answer()).orElseThrow();
    }

    public Teacher author(QuestionEvaluation questionEvaluation, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                ResolverUtils.getProxy(questionEvaluation.author()) : teacherRepository.findById(questionEvaluation.author()).orElseThrow();
    }





}
