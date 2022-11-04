package language.contentandrepository.persistence.entity.answerandevaluation;

import language.contentandrepository.persistence.entity.IdentifiableEntity;
import language.contentandrepository.persistence.entity.user.StudentEntity;
import language.contentandrepository.persistence.entity.utils.StringListConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import language.contentandrepository.persistence.entity.question.QuestionContentEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "question_answer")
@Getter @Setter @NoArgsConstructor
public class QuestionAnswerEntity extends IdentifiableEntity {

    @Column(name = "answers")
    @Convert(converter = StringListConverter.class)
    private List<String> answers;

    @ManyToOne
    @JoinColumn(name = "question")
    private QuestionContentEntity.ID question;

    @ManyToOne
    @JoinColumn(name = "student")
    private StudentEntity.ID student;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Entity
    @Table(name = "question_answer")
    @Getter @Setter @NoArgsConstructor
    public static class ID extends IdentifiableEntity {
    }

}