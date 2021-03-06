package org.tutor.materials.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@Getter @Setter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Access(AccessType.PROPERTY)
    UUID UUID;

    Long id;

    @Override
    public int hashCode() {
        Integer idHashCode = id.hashCode();
        Integer classNameHashCode = this.getClass().getTypeName().hashCode();
        return ((Integer)(idHashCode + classNameHashCode)).hashCode();

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() == this.getClass()) {
            try {
                if (this.getId().equals(((AbstractEntity) obj).getId())) {
                    return true;
                }
            } catch (NullPointerException e) {
                return false;
            }
        }

        return false;
    }

    @Override
    public String toString() {

        return this.getClass().getSimpleName() + "{" +
                "id=" + id +
                '}';
    }
}
