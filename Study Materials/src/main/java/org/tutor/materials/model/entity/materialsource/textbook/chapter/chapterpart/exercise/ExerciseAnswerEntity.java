package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswerEntity;
import org.tutor.materials.model.entity.users.StudentEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "exercise_answer")
@Getter @Setter @NoArgsConstructor
public class ExerciseAnswerEntity extends AbstractEntity {

    @ManyToOne
    ExerciseContentEntity exerciseContent;

    @OneToOne
    StudentEntity student;

    Date createdAt;

    @ManyToMany
    List<QuestionAnswerEntity> answers;

}