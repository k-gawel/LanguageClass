package language.contentandrepository.persistence.entity.textbook;

import language.contentandrepository.persistence.entity.IdentifiableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "example")
@Getter @Setter @NoArgsConstructor
public class ExampleEntity extends IdentifiableEntity {

    private String title;

    private String content;

    @Entity
    @Table(name = "example")
    public static class ID extends IdentifiableEntity {
    }


}
