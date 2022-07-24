package service;

import model.domain.*;
import model.input.QuestionEvaluationInput;
import model.repository.ChooseAWordQuestionRepository;
import model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChooseAWordEvaluator {

    private final QuestionEvaluationCreator creator;
    private final ChooseAWordQuestionRepository questionRepository;

    @Autowired
    public ChooseAWordEvaluator(QuestionEvaluationCreator creator, ChooseAWordQuestionRepository questionRepository) {
        this.creator = creator;
        this.questionRepository = questionRepository;
    }

    public QuestionEvaluation evaluate(QuestionAnswer answer) {
        var correctAnswers = getCorrectAnswers(answer);

        var evaluations = getEvaluations(answer, correctAnswers);

        var authorId = new ID<>(Teacher.class, "ADMIN");
        var answerId = answer.id();
        var score = calculateScore(evaluations);
        var comments = evaluations.stream().map(Object::toString).toList();

        var input = new QuestionEvaluationInput(answerId, authorId, comments, score);

        return creator.create(input);
    }

    private ArrayList<Boolean> getEvaluations(QuestionAnswer answer, List<List<String>> correctAnswers) {
        if(answer.answers().size() != correctAnswers.size())
            throw new IllegalArgumentException();var size = correctAnswers.size();

        var evaluations = new ArrayList<Boolean>();
        for (int i = 0; i < size; i++) {
            var correct = correctAnswers.get(i);
            var actualAnswer = answer.answers().get(i);
            var isAnswerCorrect = correct.contains(actualAnswer);
            evaluations.add(isAnswerCorrect);
        }
        return evaluations;
    }

    private List<List<String>> getCorrectAnswers(QuestionAnswer answer) {
        return questionRepository
                .findById((ID<ChooseAWordQuestion>)answer.question())
                .orElseThrow()
                .correctAnswers();
    }

    private int calculateScore(List<Boolean> evaluatedAnswers) {
        var scored = (float) evaluatedAnswers.stream().mapToInt(i -> i ? 1 : 0).sum();
        var size = (float) evaluatedAnswers.size();
        var score = scored / size * 100;
        return (int) score;
    }

}
