package org.tutor.materials.service.materialsource.question.fillaword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.dto.input.chooseaword.ClosedQuestionAnswerInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.QuestionContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.FillAWordQuestion;
import org.tutor.materials.repository.QuestionAnswerRepository;
import org.tutor.materials.repository.QuestionContentRepository;

import java.util.Objects;

@Service
class QuestionAnswerCreator {

    private final QuestionContentRepository contentRepository;
    private final QuestionAnswerRepository answerRepository;

    @Autowired
    QuestionAnswerCreator(QuestionContentRepository contentRepository, QuestionAnswerRepository answerRepository) {
        this.contentRepository = contentRepository;
        this.answerRepository = answerRepository;
    }

    QuestionAnswer answer(FillAWordQuestion question, ClosedQuestionAnswerInput input) {
        var answer = getAnswer(question, input);
        answer = answerRepository.save(answer);
        return answer;
    }


    private QuestionAnswer getAnswer(QuestionContent question, ClosedQuestionAnswerInput input) {
        return input.answerId() != null ?
                getExistingAnswer(question, input.answerId()) :
                fromInput(question, input);
     }

    private QuestionAnswer fromInput(QuestionContent question, ClosedQuestionAnswerInput input) {
        var answer = new QuestionAnswer();
        answer.setQuestion(question);
        var trimmedAnswers = Objects.requireNonNull(input.answers()).stream()
                        .map(a -> a.replaceAll("\\s{2,}", " "))
                        .toList();
        answer.setAnswers(trimmedAnswers);
        return answer;
    }

    private QuestionAnswer getExistingAnswer(QuestionContent question, Long answerId) {
        return answerRepository
                .findById(answerId)
                .filter(a -> a.getQuestion().equals(question))
                .orElseThrow();
    }


}
