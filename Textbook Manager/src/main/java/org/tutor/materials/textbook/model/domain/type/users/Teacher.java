package org.tutor.materials.textbook.model.domain.type.users;

import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;

public record Teacher(
        ID<Teacher> id,
        String name
) implements Domain, AppUser {

    @Override
    public UserRole role() {
        return UserRole.TEACHER;
    }

}
