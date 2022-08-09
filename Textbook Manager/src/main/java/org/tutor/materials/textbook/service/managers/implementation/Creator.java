package org.tutor.materials.textbook.service.managers.implementation;

import org.tutor.materials.textbook.model.entity.shared.AbstractEntity;
import org.tutor.materials.textbook.service.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class Creator {

    @PersistenceContext
    protected EntityManager entityManager;

    protected final DAO dao;

    protected Creator(DAO dao) {
        this.dao = dao;
    }

    protected void saveNewEntity(AbstractEntity entity) {
        if(entity.getId() != null)
            throw new IllegalArgumentException(entity + " already has an id. It's not new.");
        var id = generateId(entity);
        entity.setId(id);
        dao.save(entity);
    }

    private String generateId(AbstractEntity entity) {
        var baseId= entity.generateId();
        var idsCount = getIdsCount(baseId, entity.getClass());
        var id = idsCount > 0 ? baseId + "__" + idsCount : baseId;
        return id;
    }

    private long getIdsCount(String id, Class<? extends AbstractEntity> entityClass) {
        var entityName = entityClass.getSimpleName();
        var sql = "SELECT count(e) FROM " + entityName + " e WHERE e.id = :id OR e.id LIKE :idRegex";
        return entityManager.createQuery(sql, Long.class)
                .setParameter("id", id)
                .setParameter("idRegex", id + "__%")
                .getSingleResult();
    }

}
