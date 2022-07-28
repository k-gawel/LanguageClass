package endpoints;

import model.domain.*;
import model.repository.QuestionAnswerRepository;
import model.repository.criteria.QuestionAnswerCriteria;
import service.QuestionAnswerCreator;

import java.util.List;

public class QuestionEvaluationFilter {

    private final QuestionAnswerRepository questionAnswerRepository;

    public QuestionEvaluationFilter(QuestionAnswerRepository questionAnswerRepository) {
        this.questionAnswerRepository = questionAnswerRepository;
    }

    /**
     * @param accessor
     * @param evaluations
     * @return all evaluations that are accessible for user.
     * When user is teacher, every evaluation from the list is returned.
     * When user is student, only evaluations for his answers are returned.
     */
    public List<QuestionEvaluation> filter(ID<? extends AppUser> accessor, List<QuestionEvaluation> evaluations) {
        if(accessor.type().equals(Teacher.class))
            return evaluations;

        List<ID<QuestionAnswer>> answersByStudent = getAnswersByStudent((ID<Student>) accessor, evaluations);

        return evaluations.stream().filter(e -> answersByStudent.contains(e.answer())).toList();
    }

    /**
     * @param accessor
     * @param evaluations
     * @return answers from evaluations that were made by student
     */
    private List<ID<QuestionAnswer>> getAnswersByStudent(ID<Student> accessor, List<QuestionEvaluation> evaluations) {
        QuestionAnswerCriteria criteria = getCriteria(accessor, evaluations);

        return questionAnswerRepository
                .findByCriteria(criteria).stream()
                .map(QuestionAnswer::id).toList();
    }

    /**
     * @param studentID
     * @param evaluations
     * @return QuestionAnswerCriteria that returns all question answers that are made by @student and evaluated in @evaluations
     */
    private QuestionAnswerCriteria getCriteria(ID<Student> studentID, List<QuestionEvaluation> evaluations) {
        var answerIds = evaluations.stream()
                .map(QuestionEvaluation::answer)
                .map(ID::id)
                .toList();

        var studentIds = List.of(studentID.id());

        return new QuestionAnswerCriteria(answerIds, null, studentIds, null, null, null, null);
    }

}
