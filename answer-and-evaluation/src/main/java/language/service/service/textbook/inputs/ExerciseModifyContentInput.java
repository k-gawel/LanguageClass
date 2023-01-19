package language.service.service.textbook.inputs;

import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.textbook.Exercise;

public record ExerciseModifyContentInput(
        Exercise exercise,
        Question question,
        Integer newPlace
) {
}
