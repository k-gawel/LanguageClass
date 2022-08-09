package org.tutor.materials.textbook.model.domain.input;

import org.tutor.materials.textbook.model.domain.type.users.UserRole;

public record AppUserInput(
        String name,
        String password,
        UserRole role
) {
}
