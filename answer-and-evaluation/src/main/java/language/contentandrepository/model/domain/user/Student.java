package language.contentandrepository.model.domain.user;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;

public record Student(
        DomainID<Student> id,
        String name
) implements Domain, AppUser {
}
