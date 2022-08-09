package org.tutor.materials.textbook.service.events.events;

import org.tutor.materials.textbook.model.domain.input.ChapterInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.shared.Errors;

public class ChapterCreationEvent extends Event<ChapterInput, Chapter> {

    public ChapterCreationEvent(ID<? extends AppUser> accessor, ChapterInput input, Chapter chapter, Errors errors) {
        super(accessor, input, chapter, errors);
    }

    public static class Builder extends Event.Builder<ChapterCreationEvent, ChapterInput, Chapter> {

        public Builder(ID<? extends AppUser> accessor, ChapterInput input) {
            super(accessor, input);
        }

        @Override
        public ChapterCreationEvent success(Chapter result) {
            return new ChapterCreationEvent(accessor, input, result, null);
        }

        @Override
        public ChapterCreationEvent error(Errors errors) {
            return new ChapterCreationEvent(accessor, input, null, errors);
        }
    }

    public static ChapterCreationEvent.Builder create(ID<? extends AppUser> accessor, ChapterInput input) {
        return new ChapterCreationEvent.Builder(accessor, input);
    }


}
