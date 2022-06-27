package org.tutor.materials.model.entity.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.domain.interfaces.UUID;
import org.tutor.materials.model.entity.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter @Setter @NoArgsConstructor
public class AppUserEntity extends AbstractEntity implements Identifiable {

    String name;
    String password;

    @Override
    public UUID uuid() {
        return new UUID(this.getUUID(), AppUserEntity.class);
    }

}
