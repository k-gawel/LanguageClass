package language.contentandrepository.model.domain.user;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;

public record Teacher(
        DomainID<Teacher> id,
        String name
) implements AppUser, Domain {

    public Teacher(DomainID<Teacher> id) {
        this(id, null);
    }

}
