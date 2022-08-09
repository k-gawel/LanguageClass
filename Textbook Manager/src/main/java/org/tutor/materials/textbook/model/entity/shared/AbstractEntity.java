package org.tutor.materials.textbook.model.entity.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@MappedSuperclass
@NoArgsConstructor
@Getter @Setter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "key")
    private Long key;

    @NaturalId
    @Column(name = "id")
    private String id;

    public abstract String generateId();

    protected String generateIdFromString(String string) {
        return string.toLowerCase().replaceAll("\\s+", "_");
    }

    @Override
    public int hashCode() {
        Integer idHashCode = getId().hashCode();
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
                getId() +
                '}';
    }
}
