package language.contentandrepository.repository.impl.answerandevaluation;

import language.contentandrepository.criteria.CriteriaUtils;
import language.contentandrepository.criteria.answerandevaluation.ExerciseAnswerCriteria;
import language.contentandrepository.criteria.answerandevaluation.ExerciseEvaluationCriteria;
import language.contentandrepository.criteria.answerandevaluation.QuestionAnswerCriteria;
import language.contentandrepository.criteria.answerandevaluation.QuestionEvaluationCriteria;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseEvaluation;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.repository.answerandevalution.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static language.contentandrepository.criteria.CriteriaUtils.containsOrEmpty;
import static language.contentandrepository.criteria.CriteriaUtils.isBetween;

@Repository
@RequiredArgsConstructor
public class AnswerAndEvaluationRepositoryImpl implements AnswerAndEvaluationRepository {

    private final ExerciseAnswerRepository exerciseAnswerRepository;
    private final ExerciseEvaluationRepository exerciseEvaluationRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final QuestionEvaluationRepository questionEvaluationRepository;

    @Override
    public List<ExerciseAnswer> find(ExerciseAnswerCriteria criteria) {
        Predicate<ExerciseAnswer> predicate = exerciseAnswer ->
                containsOrEmpty(criteria.ids(), exerciseAnswer.id()) &&
                containsOrEmpty(criteria.students(), exerciseAnswer.author()) &&
                containsOrEmpty(criteria.exercises(), exerciseAnswer.exercise()) &&
                isBetween(criteria.createdAfter(), criteria.createdBefore(), exerciseAnswer.createdAt()) &&
                !CriteriaUtils.equals(criteria.evaluated(), exerciseEvaluationRepository.find(e -> e.answer().equals(exerciseAnswer.id())).isEmpty());

        return exerciseAnswerRepository.find(predicate);
    }

    @Override
    public List<QuestionAnswer> find(QuestionAnswerCriteria criteria) {
        Predicate<QuestionAnswer> predicate = questionAnswer -> containsOrEmpty(criteria.ids(), questionAnswer.id()) &&
                containsOrEmpty(criteria.questions(), questionAnswer.question()) &&
                containsOrEmpty(criteria.students(), questionAnswer.student()) &&
                isBetween(criteria.createdAfter(), criteria.createdBefore(), questionAnswer.createdAt()) &&
                !CriteriaUtils.equals(criteria.evaluated(), questionEvaluationRepository.find(e -> e.answer().equals(questionAnswer.id())).isEmpty());

        return questionAnswerRepository.find(predicate);
    }

    @Override
    public List<ExerciseEvaluation> find(ExerciseEvaluationCriteria criteria) {
        Predicate<ExerciseEvaluation> predicate = exerciseEvaluation -> {
            var ref = new Object() {
                ExerciseAnswer exerciseAnswer = null;
            };
            Supplier<ExerciseAnswer> getAnswer = () -> {
                ref.exerciseAnswer = ref.exerciseAnswer == null ?
                        exerciseAnswerRepository.getById(exerciseEvaluation.answer()) : ref.exerciseAnswer;
                return ref.exerciseAnswer;
            };

            return containsOrEmpty(criteria.ids(), exerciseEvaluation.id()) &&
                    containsOrEmpty(criteria.answers(), exerciseEvaluation.answer()) &&
                    containsOrEmpty(criteria.teachers(), exerciseEvaluation.author()) &&
                    containsOrEmpty(criteria.answers(), exerciseEvaluation.answer()) &&
                    isBetween(criteria.createdAfter(), criteria.createdBefore(), exerciseEvaluation.createdAt()) &&
                    containsOrEmpty(criteria.students(), getAnswer.get().author()) &&
                    containsOrEmpty(criteria.exercises(), getAnswer.get().id());
        };

        return exerciseEvaluationRepository.find(predicate);
    }

    @Override
    public List<QuestionEvaluation> find(QuestionEvaluationCriteria criteria) {

        Predicate<QuestionEvaluation> predicate = questionEvaluation -> {
            var ref = new Object() {
                QuestionAnswer questionAnswer = null;
            };
            Supplier<QuestionAnswer> getAnswer = () -> {
                ref.questionAnswer = ref.questionAnswer == null ?
                        questionAnswerRepository.getById(questionEvaluation.answer()) : ref.questionAnswer;
                return ref.questionAnswer;
            };

            return containsOrEmpty(criteria.ids(), questionEvaluation.id()) &&
                    containsOrEmpty(criteria.answers(), questionEvaluation.answer()) &&
                    containsOrEmpty(criteria.teachers(), questionEvaluation.author()) &&
                    containsOrEmpty(criteria.students(), questionEvaluation) &&
                    containsOrEmpty(criteria.answers(), getAnswer.get()) &&
                    containsOrEmpty(criteria.questions(), getAnswer.get().question());
        };

        return questionEvaluationRepository.find(predicate);
    }


}
