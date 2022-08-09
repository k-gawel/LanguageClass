package org.tutor.materials.textbook.model.entity.answerandevaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.textbook.model.entity.content.ExerciseContentEntity;
import org.tutor.materials.textbook.model.entity.shared.OrderedContentContainerEntity;
import org.tutor.materials.textbook.model.entity.users.StudentEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "exercise_answer")
@Getter @Setter @NoArgsConstructor
public class ExerciseAnswerEntity extends OrderedContentContainerEntity<QuestionAnswerEntity> {

    @ManyToOne
    @JoinColumn(name = "content")
    ExerciseContentEntity exerciseContent;

    @OneToOne
    @JoinColumn(name = "student")
    private StudentEntity student;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToMany
    @JoinTable(
            name = "exercise_answer_answers",
            joinColumns = @JoinColumn(name = "exercise_answer"),
            inverseJoinColumns = @JoinColumn(name = "question_answer", referencedColumnName = "key")
    )
    List<QuestionAnswerEntity> content;

    @ElementCollection
    @CollectionTable(name = "exercise_answer_answers",
                    joinColumns = @JoinColumn(name = "exercise_answer"))
    @Column(name = "question_answer")
    List<String> contentIds;

    @Override
    public String generateId() {
        return "answer-for-exercise-" + exerciseContent.getId() + "-by-" + student.getId();
    }
}