package language.service.service.answerandevaluation.implementations;

import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.repository.impl.BaseStudentRepository;
import language.contentandrepository.repository.impl.answerandevaluation.BaseQuestionAnswerRepository;
import language.contentandrepository.repository.impl.answerandevaluation.BaseQuestionEvaluationRepository;
import language.contentandrepository.repository.impl.answerandevaluation.BaseQuestionRepository;
import language.service.service.answerandevaluation.inputs.QuestionAnswerCreateInput;
import language.service.service.answerandevaluation.inputs.QuestionAnswerUpdateInput;
import language.service.service.answerandevaluation.services.QuestionAnswerService;
import language.service.service.eventpublisher.DomainEvent;
import language.service.service.eventpublisher.PublishEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("questionAnswerService")
@RequiredArgsConstructor
public class PublisherQuestionAnswerService implements QuestionAnswerService {

    private final BaseQuestionRepository questionRepository;
    private final BaseStudentRepository studentRepository;
    private final BaseQuestionAnswerRepository questionAnswerRepository;
    private final BaseQuestionEvaluationRepository questionEvaluationRepository;

    @PublishEvent(DomainEvent.QUESTION_ANSWER_CREATED)
    public QuestionAnswer create(QuestionAnswerCreateInput input) {
        var id = questionAnswerRepository.generateId(input.question().id().id() + "_answer");

        var answer = new QuestionAnswer(
                id,
                input.question().id(),
                input.author().id(),
                input.answers(),
                LocalDateTime.now()
        );

        questionAnswerRepository.add(answer);

        return answer;
    }

    @PublishEvent(DomainEvent.QUESTION_ANSWER_UPDATED)
    public QuestionAnswer updateQuestionAnswer(QuestionAnswerUpdateInput input) {
        var answer = input.questionAnswer();

        if(!questionEvaluationRepository.getEvaluationsByAnswer(answer.id()).isEmpty())
            throw new IllegalArgumentException("Can't update already evaluated question.");

        if(input.answers() != null) {
            answer.answers().clear();
            answer.answers().addAll(input.answers());
        }

        return answer;
    }


}
