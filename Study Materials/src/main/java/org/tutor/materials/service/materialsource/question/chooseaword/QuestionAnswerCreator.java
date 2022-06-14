package org.tutor.materials.service.materialsource.question.chooseaword;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.dto.input.chooseaword.ClosedQuestionAnswerInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.ChooseAWordContent;
import org.tutor.materials.repository.QuestionAnswerRepository;
import org.tutor.materials.repository.QuestionEvaluationRepository;
import org.tutor.materials.repository.question.ChooseAWordQuestionRepository;

import java.util.List;

@Service
class QuestionAnswerCreator {

    private final ChooseAWordQuestionRepository questionRepository;
    private final QuestionEvaluationCreator evaluationService;
    private final QuestionAnswerRepository answerRepository;
    private final QuestionEvaluationRepository evaluationRepository;

    @Autowired
    QuestionAnswerCreator(ChooseAWordQuestionRepository questionRepository, QuestionEvaluationCreator evaluationService, QuestionAnswerRepository answerRepository, QuestionEvaluationRepository evaluationRepository) {
        this.questionRepository = questionRepository;
        this.evaluationService = evaluationService;
        this.answerRepository = answerRepository;
        this.evaluationRepository = evaluationRepository;
    }


    QuestionAnswer fromInput(ClosedQuestionAnswerInput input) {
        if(input == null) return null;

        var question = questionRepository.findById(input.questionId()).orElseThrow();

        return getQuestionAnswerFromInput(input, question);
    }

    QuestionAnswer answer(ChooseAWordContent question, List<String> answers) {
        if(answers == null) return null;

        validateAnswers(question, answers);

        var answer = saveQuestionAnswer(question, answers);
        evaluationService.evaluate(answer);
        return answer;
    }

    @Nullable
    private QuestionAnswer getQuestionAnswerFromInput(ClosedQuestionAnswerInput input, ChooseAWordContent question) {
        if(input.answers() != null)
            return answer(question, input.answers());
        else if(input.answerId() != null)
            return answerRepository.findById(input.answerId())
                    .filter(a -> a.getQuestion().equals(question))
                    .orElse(null);
        else
            return null;
    }

    @NotNull
    private QuestionAnswer saveQuestionAnswer(ChooseAWordContent question, List<String> answers) {
        var answer = new QuestionAnswer();
        answer.setQuestion(question);
        answer.setAnswers(answers);
        answerRepository.save(answer);
        return answer;
    }

    private void validateAnswers(ChooseAWordContent question, List<String> answers) {

        if(answers.size() != question.getCorrectAnswers().size()) {
            throw new IllegalArgumentException("Expected answers size for question [" + question.getId() + "]: [" + question.getCorrectAnswers().size() + "]. Got: " + answers.size() + ".");
        }
    }

}
