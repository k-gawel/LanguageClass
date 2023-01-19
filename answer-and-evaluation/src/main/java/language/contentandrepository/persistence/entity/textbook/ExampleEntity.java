package language.contentandrepository.persistence.entity.textbook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter @NoArgsConstructor
public class ExampleEntity extends ExampleTable {

    private String title;

    private String content;

    @Entity
    public static class ID extends ExampleTable {
    }


}
