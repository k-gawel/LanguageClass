package org.tutor.materials.model.domain;

import org.tutor.materials.model.domain.interfaces.*;
import org.tutor.materials.model.questiontype.Question;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public record ExerciseContent<Q extends Question>(@Nonnull UUID<ExerciseContent> uuid,
                                                  Class<? extends Question> type,
                                                  String title,
                                                  List<UUID<QuestionContent>> questions) implements Identifiable, ContentContainer<QuestionContent<Q>>, ChapterPart<ExerciseContent> {

    public ExerciseContent(java.util.UUID uuid, Class<? extends Question> type, String title) {
        this(new UUID<ExerciseContent>(uuid, ExerciseContent.class), type, title, new ArrayList<>());
    }

    @Override
    public List<UUID<QuestionContent<Q>>> content() {
        return null;
    }

}
