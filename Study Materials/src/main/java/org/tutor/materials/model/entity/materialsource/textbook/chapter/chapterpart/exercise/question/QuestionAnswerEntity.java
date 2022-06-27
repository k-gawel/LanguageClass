package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.QuestionContentEntity;
import org.tutor.materials.model.entity.users.StudentEntity;
import org.tutor.materials.model.entity.utils.StringArrayToStringConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question_answer")
@Getter @Setter @NoArgsConstructor
public class QuestionAnswerEntity extends AbstractEntity {

    @ManyToOne
    QuestionContentEntity question;

    @ManyToOne
    StudentEntity student;

    @Column
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> answers = new ArrayList<>();


}
