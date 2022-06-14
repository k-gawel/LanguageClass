package org.tutor.materials.model.entity.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Student")
@Getter @Setter @NoArgsConstructor
public class Student extends AppUser {

}
