package language.service.service.textbook.services;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.service.service.textbook.inputs.ExerciseCreateInput;
import language.service.service.textbook.inputs.ExerciseModifyContentInput;

import java.util.List;

public interface ExerciseService {

    Exercise createExercise(ExerciseCreateInput input);
    List<DomainID<Question>> modifyContent(ExerciseModifyContentInput input);


}
