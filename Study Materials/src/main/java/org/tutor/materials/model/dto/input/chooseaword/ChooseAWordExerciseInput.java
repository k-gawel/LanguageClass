package org.tutor.materials.model.dto.input.chooseaword;

import org.tutor.materials.model.dto.input.ExerciseInput;
import org.tutor.materials.model.dto.input.QuestionInput;
import org.tutor.materials.model.questiontype.ChooseAWord;

import java.util.List;

public record ChooseAWordExerciseInput(String title,
                                       Long chapterId,
                                       Integer place,
                                       List<String> commonChoice,
                                       List<ChooseAWordQuestionInput> questions) implements ExerciseInput<ChooseAWord> {

    @Override
    public Class<ChooseAWord> type() {
        return ChooseAWord.class;
    }

}
