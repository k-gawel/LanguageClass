package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswer;
import org.tutor.materials.model.entity.users.Student;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class ExerciseAnswer extends AbstractEntity {

    @ManyToOne
    ExerciseContent exerciseContent;

    @OneToOne
    Student student;

    Date createdAt;

    @ManyToMany
    List<QuestionAnswer> answers;

}