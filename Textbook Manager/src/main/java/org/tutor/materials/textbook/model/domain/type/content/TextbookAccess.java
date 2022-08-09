package org.tutor.materials.textbook.model.domain.type.content;

import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.users.Student;

public record TextbookAccess (
        boolean hasAccess,
        ID<Student> student
) {
}
