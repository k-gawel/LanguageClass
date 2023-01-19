package language.service.service.textbook.implementations;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.repository.question.QuestionRepository;
import language.contentandrepository.repository.textbook.ExerciseRepository;
import language.contentandrepository.repository.user.TeacherRepository;
import language.service.service.eventpublisher.DomainEvent;
import language.service.service.eventpublisher.PublishEvent;
import language.service.service.textbook.inputs.ExerciseCreateInput;
import language.service.service.textbook.inputs.ExerciseModifyContentInput;
import language.service.service.textbook.services.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @PublishEvent(DomainEvent.EXERCISE_CONTENT_MODIFIED)
    public List<DomainID<Question>> modifyContent(ExerciseModifyContentInput input) {
        Utils.modifyContent(input.exercise().questions(), input.question(), input.newPlace());
        return input.exercise().questions().stream().toList();
    }

    private String generateBaseId(ExerciseCreateInput input) {
        return "exercise_" + input.type() + "_" + input.title().replaceAll("\\s|_+", "_");
    }

}
