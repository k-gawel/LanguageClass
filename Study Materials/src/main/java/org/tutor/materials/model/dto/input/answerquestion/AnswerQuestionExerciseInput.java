package org.tutor.materials.model.dto.input.answerquestion;

import java.util.List;

public record AnswerQuestionExerciseInput(String title,
                                          List<AnswerQuestionQuestionInput> questions) {
}
