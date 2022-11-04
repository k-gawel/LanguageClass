package language.contentandrepository.persistence.entity.answerandevaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import language.contentandrepository.persistence.entity.IdentifiableEntity;
import language.contentandrepository.persistence.entity.textbook.ExerciseEntity;
import language.contentandrepository.persistence.entity.user.StudentEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exercise_answer")
@Getter @Setter @NoArgsConstructor
public class ExerciseAnswerEntity extends IdentifiableEntity {

    @ManyToOne
    @JoinColumn(name = "exercise")
    private ExerciseEntity.ID exercise;

    @ManyToOne
    @JoinColumn(name = "student")
    private StudentEntity.ID student;

    @OneToMany
    @JoinTable(name = "exercise_answer_answers",
               joinColumns = @JoinColumn(name = "exercise_answer"),
               inverseJoinColumns = @JoinColumn(name = "question_answer")
    )
    private List<QuestionAnswerEntity.ID> questions;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Entity
    @Table(name = "exercise_answer")
    @Getter @Setter @NoArgsConstructor
    public static class ID extends IdentifiableEntity{
    }
}
