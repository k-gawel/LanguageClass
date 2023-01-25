package language.graphql.answerandevaluation.binder;

import language.contentandrepository.repository.answerandevalution.ExerciseAnswerRepository;
import language.contentandrepository.repository.textbook.ExerciseRepository;
import language.graphql.answerandevaluation.input.ExerciseAnswerInput;
import language.graphql.shared.InputBinder;
import language.service.service.answerandevaluation.inputs.ExerciseAnswerUpdateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateExerciseAnswerInputBinder implements InputBinder<ExerciseAnswerInput, ExerciseAnswerUpdateInput> {

    private final ExerciseAnswerRepository exerciseAnswerRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseAnswerInputBinderHelper helper;

    @Override
    public ExerciseAnswerUpdateInput bind(ExerciseAnswerInput source) {
        var exerciseAnswer = exerciseAnswerRepository.getById(source.id());
        var exercise = exerciseRepository.getById(exerciseAnswer.exercise());
        var questionAnswers = helper.getQuestionAnswers(exercise, source.questionAnswers(), source.questionAnswerIds());

        return new ExerciseAnswerUpdateInput(
                exerciseAnswer,
                questionAnswers
        );
    }

    @Override
    public Class<ExerciseAnswerInput> consumes() {
        return ExerciseAnswerInput.class;
    }

    @Override
    public Class<ExerciseAnswerUpdateInput> generates() {
        return ExerciseAnswerUpdateInput.class;
    }

}
