package org.tutor.materials.textbook.model.domain.input;

import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.users.Student;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;
import org.tutor.materials.textbook.shared.Errors;

import java.util.List;
import java.util.Optional;

public record TextbookInput(
        String title,
        ID<Teacher> creator,
        boolean isPublic,
        List<ID<Student>> allowedStudents
) {

    public Optional<Errors> validate() {
        return Optional.empty();
    }

}
