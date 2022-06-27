package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question;

import org.jetbrains.annotations.NotNull;
import org.tutor.materials.model.questiontype.AnswerAQuestion;
import org.tutor.materials.model.questiontype.ChooseAWord;
import org.tutor.materials.model.questiontype.FillAWord;
import org.tutor.materials.model.questiontype.Question;

import java.util.Arrays;
import java.util.function.Predicate;

public enum QuestionType {

    CHOOSE_A_WORD(ChooseAWord.class, "choose-a-word"),
    FILL_A_WORD(FillAWord.class, "fill-a-word"),
    ANSWER_A_QUESTION(AnswerAQuestion.class, "answer-a-question");

    final String typeString;
    final Class<? extends Question> type;

    QuestionType(Class<? extends Question> type, String typeString) {
        this.type = type;
        this.typeString = typeString;
    }

    public Class<? extends Question> type() {
        return type;
    }

    public String typeString() {
        return typeString;
    }

    public static QuestionType getByClass(@NotNull Class<? extends Question> typeClass) {
        return findBy((t) -> t.type.equals(typeClass));
    }

    public static QuestionType getByString(@NotNull String typeString) {
        return findBy((t) -> t.typeString.equals(typeString));
    }

    private static QuestionType findBy(Predicate<? super QuestionType> predicate) {
        return Arrays.stream(values()).filter(predicate).findFirst().orElseThrow();
    }

}
