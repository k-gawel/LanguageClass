package language.contentandrepository.persistence.entity.user;

import language.contentandrepository.persistence.entity.IdentifiableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Getter @Setter @NoArgsConstructor
public class AppUserEntity extends IdentifiableEntity {

    private String name;

    private String password;

    @Entity
    @Table(name = "app_user")
    @Getter @Setter @NoArgsConstructor
    public static class ID extends IdentifiableEntity {
    }

}
