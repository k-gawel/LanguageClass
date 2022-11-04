package language.contentandrepository.persistence.entity.answerandevaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import language.contentandrepository.persistence.entity.IdentifiableEntity;
import language.contentandrepository.persistence.entity.user.TeacherEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private String comments;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Entity
    @Table(name = "question_evaluation")
    @Getter @Setter @NoArgsConstructor
    public static class ID extends IdentifiableEntity {
    }

}
