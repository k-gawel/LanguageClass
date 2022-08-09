package org.tutor.materials.textbook.model.entity.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("STUDENT")
@Getter @Setter @NoArgsConstructor
public class StudentEntity extends AppUserEntity {

    public StudentEntity(String id ) {
        setId(id);
    }

    @Override
    public String generateId() {
        return "student-" + name;
    }
}
