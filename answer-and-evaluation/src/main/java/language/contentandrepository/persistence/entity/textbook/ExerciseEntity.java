package language.contentandrepository.persistence.entity.textbook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import language.contentandrepository.persistence.entity.question.QuestionContentEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class ExerciseEntity extends ExerciseTable {

    private String title;

    @Column(name = "question_type")
    private String questionType;

    @OneToMany
    @JoinTable(name = "exercise_content_questions",
               joinColumns = @JoinColumn(name = "exercise"),
               inverseJoinColumns = @JoinColumn(name = "question")
    )
    private List<QuestionContentEntity.ID> questions;


    @Entity
    @Getter @Setter @NoArgsConstructor
    public static class ID extends ExerciseTable {

    }

}
