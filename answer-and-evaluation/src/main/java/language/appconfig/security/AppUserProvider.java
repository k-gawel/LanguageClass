package language.appconfig.security;

import java.util.Optional;

public interface AppUserProvider {

    Optional<AppUserDetails> findByToken(String token);

}