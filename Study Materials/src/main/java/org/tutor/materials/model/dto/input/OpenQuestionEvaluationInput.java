package org.tutor.materials.model.dto.input;

public record OpenQuestionEvaluationInput(Long questionId,
                                          String correction,
                                          Integer rating) {
}
