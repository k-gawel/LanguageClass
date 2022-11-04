package language.contentandrepository.persistence.dao;

import language.appconfig.config.IgnoreDuringScan;
import org.springframework.data.jpa.repository.JpaRepository;
import language.contentandrepository.persistence.entity.IdentifiableEntity;
import language.contentandrepository.repository.IdsRepository;

@IgnoreDuringScan
public interface IdsJpaRepository<ID extends IdentifiableEntity>
        extends JpaRepository<ID, Long>, IdsRepository, EntityIdRepository<ID> {
}
