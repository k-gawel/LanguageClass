package language.service.service.textbook.inputs;

import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.persistence.entity.textbook.TextbookAccess;

public record TextbookModifyAccessInput(
        Student student,
        Textbook textbook,
        Teacher teacher,
        TextbookAccess access,
        boolean grant
) {
}
