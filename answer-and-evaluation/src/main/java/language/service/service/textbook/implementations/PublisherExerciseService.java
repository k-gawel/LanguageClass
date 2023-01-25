package language.service.service.textbook.implementations;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.question.QuestionType;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.repository.question.QuestionRepository;
import language.contentandrepository.repository.textbook.ExerciseRepository;
import language.contentandrepository.repository.user.TeacherRepository;
import language.service.service.eventpublisher.DomainEvent;
import language.service.service.eventpublisher.PublishEvent;
import language.service.service.textbook.inputs.ExerciseCreateInput;
import language.service.service.textbook.inputs.ExerciseModifyContentInput;
import language.service.service.textbook.inputs.ExerciseUpdateInput;
import language.service.service.textbook.services.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherExerciseService implements ExerciseService {

    private final TeacherRepository teacherRepository;
    private final ExerciseRepository exerciseRepository;
    private final QuestionRepository questionRepository;

    @PublishEvent(DomainEvent.EXERCISE_CREATED)
    public Exercise createExercise(ExerciseCreateInput input) {
        var id = exerciseRepository.generateId(generateBaseId(input));

        var newExercise = new Exercise(
                id,
                input.title(),
                input.type(),
                new ArrayList<>()
        );
        exerciseRepository.add(newExercise);
        return newExercise;
    }

    @PublishEvent(DomainEvent.EXERCISE_UPDATED)
    public Exercise updateExercise(ExerciseUpdateInput input) {
        var exercise = input.exercise();

        Optional<String> newTitle = Optional.empty();
        Optional<QuestionType> newQuestionType = Optional.empty();

        if(input.title() != null) {
            if(input.title().equals(exercise.title()))
                throw new RuntimeException("Title must be different from previous title");

            newTitle = Optional.of(input.title());
        }

        if(input.questionType() != null) {
            if(input.questionType().equals(exercise.questionType()))
                throw new RuntimeException("Question type must be different from previous.");
            if(exercise.questions().stream().anyMatch(q ->  !q.type().equals(input.questionType().questionClass())))
                throw new RuntimeException("Can't change type of exerciseAnswer while it has colliding questions already in");

            newQuestionType = Optional.of(input.questionType());
        }

        if(newQuestionType.isPresent() || newTitle.isPresent()) {
            var newExercise = new Exercise(
                    exercise.id(),
                    newTitle.orElse(exercise.title()),
                    newQuestionType.orElse(exercise.questionType()),
                    exercise.questions()
            );

            exerciseRepository.delete(exercise);
            exerciseRepository.add(newExercise);
            return newExercise;
        }

        return exercise;
    }

    @PublishEvent(DomainEvent.EXERCISE_CONTENT_MODIFIED)
    public List<DomainID<Question>> modifyContent(ExerciseModifyContentInput input) {
        Utils.modifyContent(input.exercise().questions(), input.question(), input.newPlace());
        return input.exercise().questions().stream().toList();
    }

    private String generateBaseId(ExerciseCreateInput input) {
        return "exercise_" + input.type() + "_" + input.title().replaceAll("\\s|_+", "_");
    }

}
