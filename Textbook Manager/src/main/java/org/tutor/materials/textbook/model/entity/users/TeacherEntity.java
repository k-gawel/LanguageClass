package org.tutor.materials.textbook.model.entity.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TEACHER")
@Getter @Setter @NoArgsConstructor
public class TeacherEntity extends AppUserEntity {


    @Override
    public String generateId() {
        return "tutor-" + name;
    }
}
