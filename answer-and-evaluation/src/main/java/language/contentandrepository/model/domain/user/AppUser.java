package language.contentandrepository.model.domain.user;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;

public interface AppUser extends Domain {
    DomainID<? extends AppUser> id();
    String name();
}
