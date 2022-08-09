package org.tutor.materials.textbook.service.events.events;

import org.tutor.materials.textbook.model.domain.input.ContentModificationInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.shared.Errors;

import java.time.LocalDateTime;
import java.util.List;

public class TextbookContentEvent extends Event<ContentModificationInput<Textbook, Chapter>, List<ID<Chapter>>> {

    public TextbookContentEvent(ID<? extends AppUser> accessor, ContentModificationInput input, List<ID<Chapter>> result, Errors errors) {
        super(accessor, input, result, errors, LocalDateTime.now());
    }

    public static class Builder extends Event.Builder<TextbookContentEvent, ContentModificationInput<Textbook, Chapter>, List<ID<Chapter>>> {

        public Builder(ID<? extends AppUser> accessor, ContentModificationInput input) {
            super(accessor, input);
        }

        @Override
        public TextbookContentEvent success(List<ID<Chapter>> result) {
            return new TextbookContentEvent(accessor, input, result, null);
        }

        @Override
        public TextbookContentEvent error(Errors errors) {
            return new TextbookContentEvent(accessor, input, null, errors);
        }
    }

    public static Builder create(ID<? extends AppUser> accessor,  ContentModificationInput input) {
        return new Builder(accessor, input);
    }

}
