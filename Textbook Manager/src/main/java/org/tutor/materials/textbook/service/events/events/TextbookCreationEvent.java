package org.tutor.materials.textbook.service.events.events;

import org.tutor.materials.textbook.model.domain.input.TextbookInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.shared.Errors;

import java.time.LocalDateTime;
import java.util.Optional;

public class TextbookCreationEvent extends Event<TextbookInput, ID<Textbook>> {

    public TextbookCreationEvent(ID<? extends AppUser> accessor, TextbookInput input, ID<Textbook> result, Errors errors) {
        super(accessor, input, result, errors, LocalDateTime.now());
    }

    public TextbookCreationEvent(ID<? extends AppUser> accessor, TextbookInput input, Textbook result, Errors errors) {
        super(accessor, input, Optional.ofNullable(result).map(Textbook::id).orElse(null), errors);
    }

    public static class Builder extends Event.Builder<TextbookCreationEvent, TextbookInput, ID<Textbook>> {

        public Builder(ID<? extends AppUser> accessor, TextbookInput input) {
            super(accessor, input);
        }

        public TextbookCreationEvent success(ID<Textbook> result) {
            return new TextbookCreationEvent(accessor, input, result, null);
        }

        public TextbookCreationEvent error(Errors errors) {
            return new TextbookCreationEvent(accessor, input, (ID<Textbook>) null, errors);
        }

    }

    public static TextbookCreationEvent.Builder create(ID<? extends AppUser> accessor, TextbookInput input) {
        return new Builder(accessor, input);
    }

}
