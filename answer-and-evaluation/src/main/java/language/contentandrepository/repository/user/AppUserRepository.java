package language.contentandrepository.repository.user;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.AppUser;
import language.contentandrepository.repository.ContentRepository;

public interface AppUserRepository extends ContentRepository<AppUser> {

    @Override
    default AppUser getById(DomainID<AppUser> id) {
        return findById(id).orElse(null);
    }

    @Override
    default AppUser getById(String id) {
        return findById(id).orElse(null);
    }
}
