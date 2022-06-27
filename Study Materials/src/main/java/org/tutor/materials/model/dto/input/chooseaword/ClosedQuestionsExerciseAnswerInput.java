package org.tutor.materials.model.dto.input.chooseaword;

import java.util.List;

public record ClosedQuestionsExerciseAnswerInput(Long exerciseId,
                                                 List<QuestionAnswerInput> answers) {
}
