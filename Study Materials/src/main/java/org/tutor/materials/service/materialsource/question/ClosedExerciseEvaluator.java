package org.tutor.materials.service.materialsource.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.ClosedQuestionEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.QuestionEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.ChooseAWordContent;
import org.tutor.materials.repository.ExerciseContentRepository;
import org.tutor.materials.repository.ExerciseEvaluationRepository;
import org.tutor.materials.repository.QuestionEvaluationRepository;

import java.util.List;

@Service
class ClosedExerciseEvaluator implements ClosedEvaluationCreator {


    private final ExerciseEvaluationRepository repository;
    private final ExerciseContentRepository contentRepository;
    private final QuestionEvaluationRepository questionEvaluationRepository;

    @Autowired
    ClosedExerciseEvaluator(ExerciseEvaluationRepository repository, ExerciseContentRepository contentRepository, QuestionEvaluationRepository questionEvaluationRepository) {
        this.repository = repository;
        this.contentRepository = contentRepository;
        this.questionEvaluationRepository = questionEvaluationRepository;
    }

    @Override
    public ExerciseEvaluation evaluate(ExerciseAnswer answer) {
        var questionEvaluations = questionEvaluationRepository.findByAnswers(answer.getAnswers());

        int rating = getRating(answer, questionEvaluations);
        var exerciseEvaluation = getExerciseEvaluation(answer, questionEvaluations, rating);

        return repository.save(exerciseEvaluation);
    }

    private ExerciseEvaluation getExerciseEvaluation(ExerciseAnswer answer, List<QuestionEvaluation> questionEvaluations, int rating) {
        var evaluation = new ExerciseEvaluation();
        evaluation.setQuestionEvaluations(questionEvaluations);
        evaluation.setAnswer(answer);
        evaluation.setRating(rating);
        return evaluation;
    }

    private int getRating(ExerciseAnswer answer, List<QuestionEvaluation> questionEvaluations) {
        float totalPoints = getTotalPoints(answer);
        float points = getPoints(questionEvaluations);
        return (int) Math.floor((points / totalPoints) * 100);
    }

    private float getPoints(List<QuestionEvaluation> questionEvaluations) {
        return (float) questionEvaluations.stream()
                .map(e -> (ClosedQuestionEvaluation) e)
                .map(ClosedQuestionEvaluation::getAreAnswersCorrect)
                .map(a -> a.stream().filter(e -> e)).count();
    }

    private float getTotalPoints(ExerciseAnswer answer) {
        var questions = contentRepository.getQuestions(answer.getExerciseContent());
        return (float) questions.stream()
                .map(q -> (ChooseAWordContent) q)
                .map(q -> q.getCorrectAnswers().size())
                .count();
    }


}
