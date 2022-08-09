package org.tutor.materials.textbook.model.domain.input;

import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.domain.type.users.Student;

public record TextbookAccessInput(
        ID<Textbook> textbook,
        boolean global,
        ID<Student> student,
        TextbookAccessAction action
) {
    public enum TextbookAccessAction {
        GRANT, DENY
    }

}
