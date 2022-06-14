package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.QuestionContent;
import org.tutor.materials.model.entity.users.Student;
import org.tutor.materials.model.entity.utils.StringArrayToStringConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class QuestionAnswer extends AbstractEntity {

    @ManyToOne
    QuestionContent question;

    @ManyToOne
    Student student;

    @Column
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> answers = new ArrayList<>();


}
