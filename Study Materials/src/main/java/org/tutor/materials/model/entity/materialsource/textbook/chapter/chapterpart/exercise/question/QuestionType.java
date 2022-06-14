package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question;

import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.QuestionContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.ChooseAWordContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.FillAWordQuestion;

public enum QuestionType {

    CHOOSE_A_WORD(ChooseAWordContent.class, "choose-a-word"),
    FILL_A_WORD(FillAWordQuestion.class, "fill-a-word");

    final String typeString;
    final Class<? extends QuestionContent> type;

    QuestionType(Class<? extends QuestionContent> type, String typeString) {
        this.type = type;
        this.typeString = typeString;
    }
}
