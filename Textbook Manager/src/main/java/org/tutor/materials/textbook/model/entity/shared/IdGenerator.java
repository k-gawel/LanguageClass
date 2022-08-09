package org.tutor.materials.textbook.model.entity.shared;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.persistence.Table;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Optional;

public class IdGenerator implements IdentifierGenerator  {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        if(o instanceof AbstractEntity entity) {
            return generate(sharedSessionContractImplementor, entity);
        } else {
            throw new IllegalArgumentException("Wrong generator chosen. Using AbstractEntity Id generator for " + o.getClass().getName());
        }
    }

    private <E extends AbstractEntity> Serializable generate(SharedSessionContractImplementor implementor, E entity) {
        var id = entity.generateId();

        var suffix = getSuffix(implementor, id, entity.getClass());

        return id + suffix;
    }

    private String getSuffix(SharedSessionContractImplementor session, String id, Class<? extends AbstractEntity> entityClass) {
        var idsCount = getIdsCount(session, id, entityClass);
        return idsCount > 0 ? ("-" + String.valueOf(idsCount)) : "";
    }

    private int getIdsCount(SharedSessionContractImplementor implementor, String prefix, Class<? extends AbstractEntity> entityClass) {
    try {
            var connection = implementor.connection();
            var statement = connection.createStatement();
            var sql = generateQuery(prefix, entityClass);
            var resultSet = statement.executeQuery(sql);
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private String generateQuery(String id, Class<? extends AbstractEntity> entityClass) {
        var tableName = getTableName(entityClass);
        return String.format("SELECT count(id) as idCount FROM %1$s WHERE id LIKE '%2$s%%'", tableName, id);
    }

    private String getTableName(Class<? extends AbstractEntity> entityClass) {
       return Optional.ofNullable(entityClass.getAnnotation(Table.class))
                .map(Table::name)
                .orElseGet(entityClass::getSimpleName);
    }

}
