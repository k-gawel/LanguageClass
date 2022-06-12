package org.tutor.materials.service.materialsource;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.dto.input.chooseaword.ClosedQuestionAnswerInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.service.materialsource.question.chooseaword.ChooseAWordService;

import java.util.List;

@Service
public class ExerciseAnswerService {

    private final ChooseAWordService chooseAWordService;

    @Autowired
    public ExerciseAnswerService(ChooseAWordService chooseAWordService) {
        this.chooseAWordService = chooseAWordService;
    }

    public ExerciseAnswer answerClosedExercise(@NotNull ExerciseContent exercise, @NotNull List<ClosedQuestionAnswerInput> answers) {
        return switch (exercise.getType()) {
            case CHOOSE_A_WORD -> chooseAWordService.answer(exercise, answers);
        };
    }

}
