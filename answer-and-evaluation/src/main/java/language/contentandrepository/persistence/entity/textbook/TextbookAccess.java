package language.contentandrepository.persistence.entity.textbook;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.model.domain.user.Student;

import java.util.Set;

public record TextbookAccess(
        DomainID<Textbook> id,
        boolean isPublic,
        Set<DomainID<Student>> allowedStudents
) implements Domain {
}
