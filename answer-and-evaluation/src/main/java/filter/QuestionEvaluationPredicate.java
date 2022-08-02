package filter;

import model.domain.user.AppUser;
import model.domain.ID;
import model.domain.evaluation.QuestionEvaluation;
import model.domain.user.Teacher;
import model.repository.QuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionEvaluationPredicate implements ModelPredicate<QuestionEvaluation> {

    private final QuestionAnswerRepository answerRepository;

    @Autowired
    public QuestionEvaluationPredicate(QuestionAnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public boolean hasAccess(ID<? extends AppUser> user, QuestionEvaluation evaluation) {
        var answer = answerRepository.findById(evaluation.answer()).
                orElseThrow();

        return user.type().equals(Teacher.class) || answer.student().equals(user);
    }

}
