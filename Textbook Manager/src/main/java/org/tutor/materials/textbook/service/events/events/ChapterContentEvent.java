package org.tutor.materials.textbook.service.events.events;

import org.tutor.materials.textbook.model.domain.input.ContentModificationInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.content.ChapterContent;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.shared.Errors;

import java.time.LocalDateTime;
import java.util.List;

public class ChapterContentEvent extends Event<ContentModificationInput<Chapter, ChapterContent>, List<ID<ChapterContent>>> {
    
    public ChapterContentEvent(ID<? extends AppUser> accessor, ContentModificationInput<Chapter, ChapterContent> input, List<ID<ChapterContent>> result, Errors errors) {
        super(accessor, input, result, errors, LocalDateTime.now());
    }

    public static class Builder extends Event.Builder<ChapterContentEvent,
                                                      ContentModificationInput<Chapter, ChapterContent>,
                                                      List<ID<ChapterContent>>> {

        Builder(ID<? extends AppUser> accessor, ContentModificationInput<Chapter, ChapterContent> input) {
            super(accessor, input);
        }

        @Override
        public ChapterContentEvent success(List<ID<ChapterContent>> result) {
            return new ChapterContentEvent(accessor, input, result, null);
        }

        @Override
        public ChapterContentEvent error(Errors errors) {
            return new ChapterContentEvent(accessor, input, null, errors);
        }
    }

    public static Builder create(ID<? extends AppUser> accessor, ContentModificationInput<Chapter, ChapterContent> input) {
        return new Builder(accessor, input);
    }

}
