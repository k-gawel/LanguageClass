package language.service.service.answerandevaluation.implementations;

import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.repository.answerandevalution.QuestionAnswerRepository;
import language.contentandrepository.repository.answerandevalution.QuestionEvaluationRepository;
import language.contentandrepository.repository.user.TeacherRepository;
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

    private final QuestionAnswerRepository questionAnswerRepository;
    private final TeacherRepository teacherRepository;
    private final QuestionEvaluationRepository questionEvaluationRepository;


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
