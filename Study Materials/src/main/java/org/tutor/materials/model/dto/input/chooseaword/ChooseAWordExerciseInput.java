package org.tutor.materials.model.dto.input.chooseaword;

import java.util.List;

public record ChooseAWordExerciseInput(String title,
                                       Long chapterId,
                                       Integer number,
                                       List<String> commonChoice,
                                       List<ChooseAWordQuestionInput> questions) {
}
