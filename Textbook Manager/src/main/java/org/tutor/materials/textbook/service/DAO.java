package org.tutor.materials.textbook.service;

import org.springframework.stereotype.Repository;
import org.tutor.materials.textbook.exceptions.NotFoundException;
import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.entity.shared.AbstractEntity;
import org.tutor.materials.textbook.model.entity.shared.OrderedContentContainerEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class DAO {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public <Q extends AbstractEntity> Optional<Q> findById(String id, Class<Q> entity) {
        try {
            var result = entityManager
                    .createQuery("SELECT e FROM " + entity.getSimpleName() + " e WHERE e.id = :id",
                                entity
            ).setParameter("id", id)
                    .getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public <Q extends AbstractEntity> Q getById(ID<? extends Domain> id, Class<Q> entity) {
        return findById(id.id(), entity)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Transactional
    public <Q extends AbstractEntity> List<Q> findByIds(Collection ids, Class<Q> entity) {
        var properIds = getIds(ids);

        return entityManager
                    .createQuery(
                            "SELECT e FROM " + entity.getSimpleName() + " e WHERE e.id IN :ids",
                            entity
                    ).setParameter("ids", properIds)
                    .getResultList();
    }

    @Transactional
    public <Q extends AbstractEntity> Optional<Q> findReference(String id, Class<Q> entityClass) {
        var sql = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.id = :id";
        try {
            var result = entityManager.createQuery(sql, entityClass)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (NonUniqueResultException e) {
            e.fillInStackTrace();
            return Optional.empty();
        }
    }

    public <Q extends AbstractEntity> List<Q> findReferences(List<?> ids, Class<Q> entityClass) {
        if(ids.stream().allMatch(i -> i.getClass().equals(String.class)))
            return (List<Q>) findReferencesFromStringIds((List<String>) ids, entityClass);
        else if(ids.stream().allMatch(i -> i.getClass().equals(ID.class)))
            return (List<Q>) findReferencesFromDomainIds((List<ID>) ids, entityClass);
        else
            throw new IllegalArgumentException("Ids must be of type ID or String");
    }

    private <Q extends AbstractEntity> List<Q> findReferencesFromDomainIds(List<ID> domainIds, Class<Q> entityClass) {
        var ids = domainIds.stream().map(ID::id).toList();
        return findReferencesFromStringIds(ids, entityClass);
    }

    private  <Q extends AbstractEntity> List<Q> findReferencesFromStringIds(List<String> ids, Class<Q> entityClass) {
        var sql = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.id IN :ids";
        return entityManager.createQuery(sql, entityClass)
                .setParameter("ids", ids)
                .getResultList();
    }

    public <Q extends AbstractEntity> Q getReference(ID<? extends Domain> id, Class<Q> entityClass) {
        return findReference(id.id(), entityClass)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public long findIdsCount(String id, Class<? extends AbstractEntity> entityClass) {
        var sql = "SELECT count(e) FROM " + entityClass.getSimpleName() + " e WHERE e.id = :id OR e.id LIKE ':id__%'";
        return entityManager.createQuery(sql, Long.class).getSingleResult();
    }

    @Transactional
    public <Q extends AbstractEntity> void save(Q entity) {
        entityManager.merge(entity);
    }

    public <P extends OrderedContentContainerEntity<C>, C extends AbstractEntity> List<String> findChildrenIds(P parent) {
        var sql = "SELECT c.id FROM " + parent.getClass().getSimpleName() + " e JOIN e.content c WHERE e = :parent";
        return entityManager.createQuery(sql, String.class)
                .setParameter("parent", parent)
                .getResultList();
    }

    protected List<String> getIds(Collection collection) {
        Function<Object, String> cast = (i) -> {
            if(i instanceof String s)
                return s;
            else if(i instanceof ID<? extends Domain> d)
                return d.id();
            else
                throw new IllegalArgumentException("Id must be of a String of ID type and is " + i.getClass());
        };

        return collection.stream().map(cast).toList();
    }


}
