package org.tutor.materials.textbook.service.events.events;

import org.tutor.materials.textbook.model.domain.input.TextbookAccessInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.TextbookAccess;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.shared.Errors;

import java.time.LocalDateTime;

public class TextbookAccessEvent extends Event<TextbookAccessInput, TextbookAccess> {

    public TextbookAccessEvent(ID<? extends AppUser> accessor, TextbookAccessInput input, TextbookAccess result, Errors errors) {
        super(accessor, input, result, errors, LocalDateTime.now());
    }

    public static class Builder extends Event.Builder<TextbookAccessEvent, TextbookAccessInput, TextbookAccess> {

        public Builder(ID<? extends AppUser> accessor, TextbookAccessInput input) {
            super(accessor, input);
        }

        @Override
        public TextbookAccessEvent success(TextbookAccess result) {
            return new TextbookAccessEvent(accessor, input, result, null);
        }

        @Override
        public TextbookAccessEvent error(Errors errors) {
            return new TextbookAccessEvent(accessor, input, null, errors);
        }
    }

    public static Builder create(ID<? extends AppUser> accessor, TextbookAccessInput input) {
        return new Builder(accessor, input);
    }

}
