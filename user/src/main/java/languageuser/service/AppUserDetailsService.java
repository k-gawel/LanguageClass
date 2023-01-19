package languageuser.service;

import languageuser.service.model.AppUserDetails;
import languageuser.service.model.AppUserEntity;
import languageuser.service.model.AppUserEntityRepository;
import languageuser.service.model.UserAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserEntityRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findAppUserEntityByUsername(username)
                .map(this::fromEntity)
                .orElse(null);
    }

    private AppUserDetails fromEntity(AppUserEntity entity) {
        return new AppUserDetails(
                entity.getUsername(),
                UserAuthority.fromStringValue(entity.getRole()).orElseThrow()
                );
    }

}
