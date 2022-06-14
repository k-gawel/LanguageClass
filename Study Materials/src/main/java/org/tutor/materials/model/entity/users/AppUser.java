package org.tutor.materials.model.entity.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.AbstractEntity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter @Setter @NoArgsConstructor
public class AppUser extends AbstractEntity {

    String name;
    String password;

}
