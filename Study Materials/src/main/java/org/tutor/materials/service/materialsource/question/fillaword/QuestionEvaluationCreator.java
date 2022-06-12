package org.tutor.materials.service.materialsource.question.fillaword;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.ClosedQuestionContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.ClosedQuestionEvaluation;
import org.tutor.materials.repository.QuestionAnswerRepository;
import org.tutor.materials.repository.QuestionContentRepository;
import org.tutor.materials.repository.QuestionEvaluationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
class QuestionEvaluationCreator {

    private final QuestionContentRepository questionContentRepository;
    private final QuestionEvaluationRepository questionEvaluationRepository;

    @Autowired
    QuestionEvaluationCreator(QuestionContentRepository questionContentRepository, QuestionAnswerRepository answerRepository, QuestionEvaluationRepository questionEvaluationRepository) {
        this.questionContentRepository = questionContentRepository;
        this.questionEvaluationRepository = questionEvaluationRepository;
    }

    ClosedQuestionEvaluation evaluate(QuestionAnswer answer) {
        var evaluation = fromAnswer(answer);
        evaluation = questionEvaluationRepository.save(evaluation);
        return evaluation;
    }

    private ClosedQuestionEvaluation fromAnswer(QuestionAnswer answer) {
        var question = (ClosedQuestionContent) questionContentRepository.getByAnswer(answer);

        var validations = validate(answer.getAnswers(), question.getCorrectAnswers());

        var evaluation = new ClosedQuestionEvaluation();
        evaluation.setAnswer(answer);
        evaluation.setAreAnswersCorrect(validations);
        return evaluation;
    }

    private List<Boolean> validate(List<String> answers, List<List<String>> correctAnswers) {
        var result = new ArrayList<Boolean>();
        for(int i = 0; i < answers.size(); i++) {
            var validation = correctAnswers.get(i).contains(answers.get(i));
            result.add(validation);
        }
        return result;
    }

    private boolean validate(String answer, String correctAnswer) {
        var normalizedAnswer = StringUtils
                .stripAccents(answer)
                .replaceAll("\\s{2,}", " ")
                .trim();

        var normalizedCorrectAnswer = StringUtils
                .stripAccents(correctAnswer);

        return normalizedAnswer.equals(normalizedCorrectAnswer);
    }

}
