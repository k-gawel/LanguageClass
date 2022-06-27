package org.tutor.materials.repository.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.domain.interfaces.UUID;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.OrderedContentContainerEntity;
import org.tutor.materials.shared.EntityAndDomainWirer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class DefaultOrderedContentContainerRepository<OWNER extends OrderedContentContainerEntity<CHILD>, CHILD extends AbstractEntity> {

    @PersistenceContext
    private EntityManager entityManager;
    private final EntityAndDomainWirer wirer;

    @Autowired
    public DefaultOrderedContentContainerRepository(EntityAndDomainWirer wirer) {
        this.wirer = wirer;
    }

    @Transactional
    public Optional<OWNER> findById(UUID<? extends Identifiable> uuid) {
        var entity = wirer.fromDomain(uuid.type());
        if(!OrderedContentContainerEntity.class.isAssignableFrom(entity))
            throw new IllegalArgumentException(entity.getSimpleName() + " doesn't extend " + OrderedContentContainerEntity.class.getSimpleName());
        var ownerEntity = (Class<OrderedContentContainerEntity<? extends AbstractEntity>>) entity;

        return (Optional<OWNER>) getSingleResult(uuid.uuid(), ownerEntity);
    }

    @Transactional
    public List<CHILD> findContent(UUID<? extends Identifiable> uuid) {
        var entity = wirer.fromDomain(uuid.type());
        var queryString = "SELECT c.content FROM " + entity.getSimpleName() + " c WHERE c.UUID = :uuid";
        System.out.println(queryString);
        return (List<CHILD>) entityManager.createQuery(queryString)
                .setParameter("uuid", uuid.uuid())
                .getResultList();
    }

    public Optional<CHILD> findContentEntity(UUID<? extends Identifiable> uuid) {
        var entity = wirer.fromDomain(uuid.type());
        return (Optional<CHILD>) getSingleResult(uuid.uuid(), entity);
    }

    @Transactional
    protected <Q extends AbstractEntity> Optional<Q> getSingleResult(java.util.UUID uuid, Class<Q> entity) {
        try {
            var queryString = "SELECT c FROM " + entity.getSimpleName() + " c WHERE c.UUID = :uuid";
            var result = entityManager.createQuery(queryString, entity)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    public void save(OWNER ownerEntity) {
        entityManager.merge(ownerEntity);
    }
}
