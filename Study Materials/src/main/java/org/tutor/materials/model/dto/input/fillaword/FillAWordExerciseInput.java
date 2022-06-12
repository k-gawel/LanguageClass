package org.tutor.materials.model.dto.input.fillaword;

import java.util.List;

public record FillAWordExerciseInput(String title,
                                    List<FillAWordQuestionInput> questions) {
}
