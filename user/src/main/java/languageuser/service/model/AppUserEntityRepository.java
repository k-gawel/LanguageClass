package languageuser.service.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserEntityRepository extends JpaRepository<AppUserEntity, String> {

    Optional<AppUserEntity> findAppUserEntityByUsernameAndPassword(String username, String password);

    Optional<AppUserEntity> findAppUserEntityByUsername(String username);

}
