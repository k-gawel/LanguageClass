package language.graphql.answerandevaluation.filter;

import language.appconfig.security.UserAuthority;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseEvaluation;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.repository.answerandevalution.ExerciseAnswerRepository;
import language.contentandrepository.repository.answerandevalution.QuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerAndEvaluationFilter {

    private final ExerciseAnswerRepository exerciseAnswerRepository;
    private final QuestionAnswerRepository questionAnswerRepository;

    public boolean hasAccess(Authentication auth, ExerciseAnswer exerciseAnswer) {
        return auth.getAuthorities().contains(UserAuthority.TEACHER) ||
                exerciseAnswer.author().id().equals(auth.getName());
    }

    public boolean hasAccess(Authentication auth, ExerciseEvaluation evaluation) {
        return auth.getAuthorities().contains(UserAuthority.TEACHER) ||
                exerciseAnswerRepository.getById(evaluation.answer()).author().id().equals(auth.getName());
    }

    public boolean hasAccess(Authentication auth, QuestionAnswer answer) {
        return auth.getAuthorities().contains(UserAuthority.TEACHER) ||
                answer.student().id().equals(auth.getName());
    }

    public boolean hasAccess(Authentication auth, QuestionEvaluation evaluation) {
        return auth.getAuthorities().contains(UserAuthority.TEACHER) ||
                questionAnswerRepository.getById(evaluation.answer()).student().id().equals(auth.getName());
    }

}
