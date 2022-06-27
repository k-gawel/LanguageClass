package org.tutor.materials.model.dto.input;

import java.util.List;

public record OpenExerciseEvaluationInput(Long exerciseAnswerId,
                                          List<OpenQuestionEvaluationInput> questionEvaluations,
                                          Integer rating) {
}
