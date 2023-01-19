package language.service.service.answerandevaluation.implementations;

import language.contentandrepository.criteria.answerandevaluation.ExerciseAnswerCriteria;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.repository.answerandevalution.AnswerAndEvaluationRepository;
import language.contentandrepository.repository.textbook.ExerciseRepository;
import language.contentandrepository.repository.user.TeacherRepository;
import language.graphql.answerandevaluation.input.ExerciseAnswerInput;
import language.service.service.answerandevaluation.inputs.ExerciseAnswerCreateInput;
import language.service.service.answerandevaluation.services.ExerciseAnswerService;
import language.service.service.eventpublisher.DomainEvent;
import language.service.service.eventpublisher.PublishEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.List;

@Service("exerciseAnswerService")
@RequiredArgsConstructor
public class PublisherExerciseAnswerService implements ExerciseAnswerService {

    private final Clock clock;
    private final TeacherRepository teacherRepository;
    private final ExerciseRepository exerciseRepository;
    private final AnswerAndEvaluationRepository answerAndEvaluationRepository;

    @PublishEvent(DomainEvent.EXERCISE_ANSWER_CREATED)
    public ExerciseAnswer createExerciseAnswer(ExerciseAnswerCreateInput input) {

        return null;
    }


    @PublishEvent(DomainEvent.EXERCISE_ANSWER_UPDATED)
    @Override
    public ExerciseAnswer updateExerciseAnswer(ExerciseAnswerInput input) {
        return null;
    }

    @Override
    public List<ExerciseAnswer> find(ExerciseAnswerCriteria criteria) {
        return answerAndEvaluationRepository.find(criteria);
    }


}
