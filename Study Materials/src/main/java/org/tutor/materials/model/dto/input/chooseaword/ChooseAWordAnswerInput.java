package org.tutor.materials.model.dto.input.chooseaword;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public record ChooseAWordAnswerInput(@NotNull Long questionId,
                                     @Nullable Long answerId,
                                     @Nullable List<String> answers) {
}
