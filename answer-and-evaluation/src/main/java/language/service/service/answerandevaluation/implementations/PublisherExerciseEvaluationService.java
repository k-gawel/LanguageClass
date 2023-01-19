package language.service.service.answerandevaluation.implementations;

import language.contentandrepository.model.domain.answerandevaluation.ExerciseEvaluation;
import language.service.service.answerandevaluation.inputs.ExerciseEvaluationCreateInput;
import language.service.service.answerandevaluation.inputs.ExerciseEvaluationUpdateInput;
import language.service.service.answerandevaluation.services.ExerciseEvaluationService;
import language.service.service.eventpublisher.DomainEvent;
import language.service.service.eventpublisher.PublishEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("exerciseEvaluationService")
@RequiredArgsConstructor
public class PublisherExerciseEvaluationService implements ExerciseEvaluationService {

    @PublishEvent(DomainEvent.EXERCISE_EVALUATION_CREATED)
    public ExerciseEvaluation create(ExerciseEvaluationCreateInput input) {
        return null;
    }

    @PublishEvent(DomainEvent.EXERCISE_EVALUATION_UPDATED)
    @Override
    public ExerciseEvaluation update(ExerciseEvaluationUpdateInput input) {
        return null;
    }

}
