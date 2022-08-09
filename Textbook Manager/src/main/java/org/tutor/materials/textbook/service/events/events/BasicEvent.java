package org.tutor.materials.textbook.service.events.events;

import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.shared.Errors;

import java.io.Serializable;

public final class BasicEvent extends Event<Serializable, Serializable> {

    public BasicEvent(ID<? extends AppUser> accessor, Serializable input, Serializable result, Errors errors) {
        super(accessor, input, result, errors);
    }

    public static class Builder extends Event.Builder<BasicEvent, Serializable, Serializable> {

        public Builder(ID<? extends AppUser> accessor, Serializable input) {
            super(accessor, input);
        }

        @Override
        public BasicEvent success(Serializable result) {
            return new BasicEvent(accessor, input, result, null);
        }

        @Override
        public BasicEvent error(Errors errors) {
            return new BasicEvent(accessor, input, null, errors);
        }
    }

    public static Builder create(ID<? extends AppUser> accessor, Serializable input) {
        return new Builder(accessor, input);
    };

}
