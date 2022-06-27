package org.tutor.materials.repository.generator;

import org.hibernate.mapping.Join;
import org.tutor.materials.model.entity.OrderedContentContainerEntity;

import javax.persistence.JoinTable;
import java.util.Arrays;

public class QueryGenerator {

    public String generateContentIdsQuery(Class<? extends OrderedContentContainerEntity> entityClass) {
        var field = Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> f.getName().equals("content"))
                .findFirst().orElseThrow();

        var annotation = Arrays.stream(field.getAnnotations())
                .filter(a -> a.annotationType().equals(JoinTable.class))
                .map(a -> (JoinTable) a)
                .findFirst().orElseThrow();

        var tableName = annotation.name();
        var contentIdTable = annotation.inverseJoinColumns()[0].name();
        var ownerIdTable = annotation.joinColumns()[0].name();

        return "SELECT cast(T." + contentIdTable + " as varchar) " +
                "FROM " + tableName + " as T " +
                "WHERE cast(T." + ownerIdTable  + " as varchar) = ?1";
    }

}
