package org.tutor.materials.model.domain;

import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.domain.interfaces.QuestionContent;
import org.tutor.materials.model.domain.interfaces.UUID;
import org.tutor.materials.model.questiontype.ChooseAWord;

import java.util.List;

public record ChooseAWordQuestionContent(UUID<ChooseAWordQuestionContent> uuid,
                                         List<String> content,
                                         List<List<String>> wordChoice,
                                         List<List<String>> correctAnswers)
        implements Identifiable, QuestionContent<ChooseAWord> {

    public ChooseAWordQuestionContent(java.util.UUID uuid, List<String> content, List<List<String>> wordChoice, List<List<String>> correctAnswers) {
        this(new UUID<ChooseAWordQuestionContent>(uuid, ChooseAWordQuestionContent.class), content, wordChoice, correctAnswers);
    }

    @Override
    public Class<ChooseAWord> type() {
        return ChooseAWord.class;
    }
}
