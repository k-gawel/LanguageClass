package language.contentandrepository.persistence.entity.user;

import language.contentandrepository.persistence.entity.IdentifiableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("TEACHER")
public class TeacherEntity extends AppUserEntity {

    @Entity
    @Table(name = "app_user")
    @Getter @Setter @NoArgsConstructor
    public static class ID extends IdentifiableEntity {
    }

}
