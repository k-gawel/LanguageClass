package org.tutor.materials.textbook.model.entity.shared;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Predicate;

public enum QuestionType {

    CHOOSE_A_WORD("choose-a-word"),
    FILL_A_WORD( "fill-a-word"),
    ANSWER_A_QUESTION( "answer-a-question");

    final String typeString;

    QuestionType(String typeString) {
        this.typeString = typeString;
    }


    public String typeString() {
        return typeString;
    }


    public static QuestionType getByString(@NotNull String typeString) {
        return findBy((t) -> t.typeString.equals(typeString));
    }

    private static QuestionType findBy(Predicate<? super QuestionType> predicate) {
        return Arrays.stream(values()).filter(predicate).findFirst().orElseThrow();
    }

}
