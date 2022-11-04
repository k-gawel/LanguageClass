package language.contentandrepository.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor
public class IdentifiableEntity  {

    @Id
    @GeneratedValue
    private Long key;

    private String id;

}
