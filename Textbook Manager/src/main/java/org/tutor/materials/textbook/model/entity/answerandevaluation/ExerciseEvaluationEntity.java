package org.tutor.materials.textbook.model.entity.answerandevaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.textbook.model.entity.shared.OrderedContentContainerEntity;
import org.tutor.materials.textbook.model.entity.users.TeacherEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercise_evaluation")
@Getter @Setter @NoArgsConstructor
public class ExerciseEvaluationEntity extends OrderedContentContainerEntity<QuestionEvaluationEntity> {

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;

    @OneToOne
    @JoinColumn(name = "answer")
    ExerciseAnswerEntity answer;

    @ManyToMany

    @JoinTable(
            name = "exercise_evaluation_question_evaluation",
            joinColumns = @JoinColumn(name = "exercise_evaluation"),
            inverseJoinColumns = @JoinColumn(name = "question_evaluation")
    )
    List<QuestionEvaluationEntity> content = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "exercise_evaluation_question_evaluation",
                    joinColumns = @JoinColumn(name = "exercise_evaluation"))
    @Column(name = "question_evaluation")
    List<String> contentIds;

    @ManyToOne
    @JoinColumn(name = "teacher")
    TeacherEntity author;

    @Override
    public String generateId() {
        return "evaluation-of-" + answer.getId() + "-by-" + author.getId();
    }
}
