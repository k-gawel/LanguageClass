package language.contentandrepository.repository;

import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.dao.IdsJpaRepository;
import language.contentandrepository.persistence.entity.IdentifiableEntity;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;

import java.util.List;
import java.util.Optional;

public class BaseContentRepository<D extends Domain, E extends IdentifiableEntity> implements ContentRepository<D> {

    private final Class<D> objectClass;
    private final EntityToModelMapper<E, D> entityToModelMapper;
    private final DomainIdGenerator<D> domainIdGenerator;
    private final DomainCache<D> domainCache;
    private final IdentifiableEntityJpaRepository<E> jpaRepository;

    public BaseContentRepository(Class<D> objectClass,
                                 EntityToModelMapper<E, D> entityToModelMapper,
                                 DomainCache<D> domainCache,
                                 IdentifiableEntityJpaRepository<E> jpaRepository,
                                 IdsJpaRepository idsJpaRepository) {
        this.objectClass = objectClass;
        this.entityToModelMapper = entityToModelMapper;
        this.domainCache = domainCache;
        this.jpaRepository = jpaRepository;

        this.domainIdGenerator = new DomainIdGenerator<D>(domainCache, idsJpaRepository);
    }

    @Override
    public Optional<D> findById(DomainID<D> id) {
        return Optional.ofNullable(domainCache.findById(id)
                .orElseGet(() -> findInJpa(id).orElse(null)));
    }

    @Override
    public List<D> getByIds(List<DomainID<D>> domainIDS) {
        return domainIDS.stream()
                .map(this::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @Override
    public DomainID<D> generateId(String baseId) {
        var idString = domainIdGenerator.generateNewId(baseId);
        return new DomainID<>(objectClass, idString);
    }

    @Override
    public boolean add(D object) {
        return domainCache.add(object);
    }

    @Override
    public boolean save(D object) {
        domainCache.delete(object);
        var entity = entityToModelMapper.toEntity(object);
        jpaRepository.save(entity);
        return true;
    }

    @Override
    public void delete(D object) {
        if (!domainCache.delete(object)) {
            jpaRepository.deleteEntityById(object.id().id());
        }
    }

    @Override
    public boolean isPersisted(D object) {
        return jpaRepository.findEntityById(object.id().id()).isPresent();
    }

    @Override
    public boolean isPresent(D object) {
        return (boolean) domainCache.findById(object.id())
                .map(i -> true)
                .orElse(jpaRepository.findEntityById(object.id().id()).isPresent());
    }

    private Optional<D> findInJpa(DomainID<D> id) {
        return jpaRepository.findEntityById(id.id())
                .map(entityToModelMapper::fromEntity);
    }

}
