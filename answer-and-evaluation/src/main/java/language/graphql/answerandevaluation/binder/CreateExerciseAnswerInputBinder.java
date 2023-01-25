package language.graphql.answerandevaluation.binder;

import language.contentandrepository.repository.textbook.ExerciseRepository;
import language.contentandrepository.repository.user.StudentRepository;
import language.graphql.answerandevaluation.input.ExerciseAnswerInput;
import language.graphql.shared.InputBinder;
import language.service.service.answerandevaluation.inputs.ExerciseAnswerCreateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateExerciseAnswerInputBinder implements InputBinder<ExerciseAnswerInput, ExerciseAnswerCreateInput> {

    private final StudentRepository studentRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseAnswerInputBinderHelper helper;

    @Override
    public ExerciseAnswerCreateInput bind(ExerciseAnswerInput source) {

        var exercise = exerciseRepository.getById(source.exercise());
        var questionAnswers = helper.getQuestionAnswers(exercise, source.questionAnswers(), source.questionAnswerIds());

        return new ExerciseAnswerCreateInput(
                studentRepository.getById(source.author()),
                exercise,
                questionAnswers
        );
    }



    @Override
    public Class<ExerciseAnswerInput> consumes() {
        return ExerciseAnswerInput.class;
    }

    @Override
    public Class<ExerciseAnswerCreateInput> generates() {
        return ExerciseAnswerCreateInput.class;
    }

}
