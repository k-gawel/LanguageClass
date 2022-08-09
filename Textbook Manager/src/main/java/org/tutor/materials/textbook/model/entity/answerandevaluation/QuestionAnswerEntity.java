package org.tutor.materials.textbook.model.entity.answerandevaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.textbook.model.entity.content.question.QuestionContentEntity;
import org.tutor.materials.textbook.model.entity.shared.AbstractEntity;
import org.tutor.materials.textbook.model.entity.shared.StringArrayToStringConverter;
import org.tutor.materials.textbook.model.entity.users.StudentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question_answer")
@Getter @Setter @NoArgsConstructor
public class QuestionAnswerEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "question")
    QuestionContentEntity question;

    @ManyToOne
    @JoinColumn(name = "student")
    StudentEntity student;

    @Column(name = "answers")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> answers = new ArrayList<>();


    @Override
    public String generateId() {
        return "answer-for-" + question.getId() + "-by-" + student.getId();
    }
}
