package org.tutor.materials.service.materialsource.question.chooseaword;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.ClosedQuestionContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.ClosedQuestionEvaluation;
import org.tutor.materials.repository.BasicRepository;
import org.tutor.materials.repository.QuestionEvaluationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
class QuestionEvaluationCreator {

    private final QuestionEvaluationRepository questionEvaluationRepository;
    private final BasicRepository<AbstractEntity> basicRepository;

    @Autowired
    public QuestionEvaluationCreator(QuestionEvaluationRepository questionEvaluationRepository, BasicRepository<AbstractEntity> basicRepository) {
        this.questionEvaluationRepository = questionEvaluationRepository;
        this.basicRepository = basicRepository;    }

    ClosedQuestionEvaluation evaluate(QuestionAnswer answer) {
        ClosedQuestionEvaluation evaluation = getClosedQuestionEvaluation(answer);

        return basicRepository.save(evaluation);
    }

    @NotNull
    private ClosedQuestionEvaluation getClosedQuestionEvaluation(QuestionAnswer answer) {
        var answers = answer.getAnswers();
        var question = (ClosedQuestionContent) answer.getQuestion();
        var correctAnswers = question.getCorrectAnswers();

        var areAnswersCorrect = getAnswersEvaluation(answers, correctAnswers);

        var evaluation = new ClosedQuestionEvaluation();
        evaluation.setAnswer(answer);
        evaluation.setAreAnswersCorrect(areAnswersCorrect);
        return evaluation;
    }

    private ArrayList<Boolean> getAnswersEvaluation(List<String> answers, List<List<String>> correctAnswers) {
        var areAnswersCorrect = new ArrayList<Boolean>();
        for(int i = 0; i < correctAnswers.size(); i++)
            areAnswersCorrect.add(correctAnswers.get(i).contains(answers.get(i)));
        return areAnswersCorrect;
    }


}
