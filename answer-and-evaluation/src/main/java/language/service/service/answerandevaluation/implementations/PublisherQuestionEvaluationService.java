package language.service.service.answerandevaluation.implementations;

import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.repository.impl.BaseTeacherRepository;
import language.contentandrepository.repository.impl.answerandevaluation.BaseQuestionAnswerRepository;
import language.contentandrepository.repository.impl.answerandevaluation.BaseQuestionEvaluationRepository;
import language.service.service.answerandevaluation.inputs.QuestionEvaluationCreateInput;
import language.service.service.eventpublisher.DomainEvent;
import language.service.service.eventpublisher.PublishEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Service("questionEvaluationService")
@RequiredArgsConstructor
public class PublisherQuestionEvaluationService {

    private final BaseQuestionAnswerRepository questionAnswerRepository;
    private final BaseTeacherRepository teacherRepository;
    private final BaseQuestionEvaluationRepository questionEvaluationRepository;


    @PublishEvent(DomainEvent.QUESTION_EVALUATION_CREATED)
    public QuestionEvaluation createQuestionEvaluation(@Valid QuestionEvaluationCreateInput input) {
        var id = questionEvaluationRepository.generateId(input.questionAnswer().id().id() + "_evaluation");

        var newEvaluation = new QuestionEvaluation(
                id,
                input.questionAnswer().id(),
                input.author().id(),
                input.comments(),
                input.score(),
                LocalDateTime.now()
        );

        questionEvaluationRepository.add(newEvaluation);
        return newEvaluation;
    }

}
