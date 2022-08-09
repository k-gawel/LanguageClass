package org.tutor.materials.textbook.model.domain.type.users;

import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;

public record Student(
        ID<Student> id,
        String name
) implements Domain, AppUser {

    public Student(String id, String name) {
        this(new ID<>(Student.class, id), name);
    }

    @Override
    public UserRole role() {
        return UserRole.STUDENT;
    }

}
