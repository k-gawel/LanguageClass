package language.contentandrepository.persistence.entity.question;

import language.contentandrepository.persistence.entity.IdentifiableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "question_content")
@Getter @Setter @NoArgsConstructor
public class QuestionContentEntity extends IdentifiableEntity {

    @Entity
    @Table(name = "question_content")
    @DiscriminatorColumn(name = "type")
    @Getter @Setter @NoArgsConstructor
    public static class ID extends IdentifiableEntity {

        @Column(name = "type", insertable = false, updatable = false)
        private String type;

    }

}
