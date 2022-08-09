package org.tutor.materials.textbook.service.events.events;

import org.tutor.materials.textbook.model.domain.input.AppUserInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.shared.AppUser;
import org.tutor.materials.textbook.shared.Errors;

import java.time.LocalDateTime;

public class UserCreationEvent extends Event<AppUserInput, AppUser> {

    public UserCreationEvent(ID accessor, AppUserInput input, AppUser result, Errors errors) {
        super(accessor, input, result, errors, LocalDateTime.now());
    }

}
