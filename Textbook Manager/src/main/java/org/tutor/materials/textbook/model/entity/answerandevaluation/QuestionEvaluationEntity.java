package org.tutor.materials.textbook.model.entity.answerandevaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.textbook.model.entity.shared.AbstractEntity;
import org.tutor.materials.textbook.model.entity.users.TeacherEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public abstract class QuestionEvaluationEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "teacher")
    TeacherEntity teacher;

    @OneToOne
    @JoinColumn(name = "answer")
    QuestionAnswerEntity answer;

    @Column(name = "rating")
    private int rating;

    @ElementCollection
    private List<String> comments;

}
