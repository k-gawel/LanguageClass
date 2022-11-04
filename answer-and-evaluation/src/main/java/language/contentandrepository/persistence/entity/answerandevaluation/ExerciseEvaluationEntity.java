package language.contentandrepository.persistence.entity.answerandevaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import language.contentandrepository.persistence.entity.IdentifiableEntity;
import language.contentandrepository.persistence.entity.user.TeacherEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exercise_evaluation")
@Getter @Setter @NoArgsConstructor
public class ExerciseEvaluationEntity extends IdentifiableEntity {

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "answer")
    private ExerciseAnswerEntity.ID answer;

    @ManyToOne
    @JoinColumn(name = "teacher")
    private TeacherEntity.ID teacher;

    @OneToMany
    @JoinTable(name = "exercise_evaluation_question_evaluation",
               joinColumns = @JoinColumn(name = "exercise_evaluation"),
               inverseJoinColumns = @JoinColumn(name = "question_evaluation")
    )
    private List<QuestionEvaluationEntity.ID> questionEvaluations;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Entity
    @Table(name = "exercise_evaluation")
    @Getter @Setter @NoArgsConstructor
    public static class ID extends IdentifiableEntity {
    }

}
