package utils.dummyrepositories;

import language.contentandrepository.model.domain.user.AppUser;
import language.contentandrepository.repository.user.AppUserRepository;

import java.util.List;

public class TestAppUserRepositoryFactory {

    private static class DummyAppUserRepository extends DummyContentRepository<AppUser> implements AppUserRepository {
        public DummyAppUserRepository() {
            super(AppUser.class);
        }

        public DummyAppUserRepository(List<AppUser> list) {
            super(list, AppUser.class);
        }
    }

    public static DummyAppUserRepository emptyRepository() {
        return new DummyAppUserRepository();
    }

    public static DummyAppUserRepository repository(List<AppUser> list) {
        return new DummyAppUserRepository(list);
    }


}
