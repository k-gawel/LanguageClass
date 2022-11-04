package language.contentandrepository.persistence.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import language.contentandrepository.persistence.entity.user.AppUserEntity;

import java.util.Optional;

public interface AppUserJpa extends JpaRepository<AppUserEntity, Long> {

    Optional<AppUserEntity> findAppUserEntityByName(String name);

    Optional<AppUserEntity> findAppUserEntityById(String id);

}
