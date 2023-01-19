package language.contentandrepository.persistence.entity.answerandevaluation;

import language.contentandrepository.persistence.entity.IdentifiableEntity;
import language.contentandrepository.persistence.entity.user.TeacherEntity;
import language.contentandrepository.persistence.entity.utils.StringListConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "question_evaluation")
@Getter @Setter @NoArgsConstructor
public class QuestionEvaluationEntity extends IdentifiableEntity {

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "answer")
    private QuestionAnswerEntity.ID answer;

    @ManyToOne
    @JoinColumn(name = "teacher")
    private TeacherEntity.ID teacher;

    @Column(name = "comments")
    @Convert(converter = StringListConverter.class)
    private List<String> comments;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Entity
    @Table(name = "question_evaluation")
    @Getter @Setter @NoArgsConstructor
    public static class ID extends IdentifiableEntity {
    }

}
