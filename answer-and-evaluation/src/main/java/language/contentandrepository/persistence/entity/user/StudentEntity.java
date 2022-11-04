package language.contentandrepository.persistence.entity.user;

import language.contentandrepository.persistence.entity.IdentifiableEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("STUDENT")
public class StudentEntity extends AppUserEntity {

    @Entity
    @Table(name = "app_user")
    public static class ID extends IdentifiableEntity {
    }

}
