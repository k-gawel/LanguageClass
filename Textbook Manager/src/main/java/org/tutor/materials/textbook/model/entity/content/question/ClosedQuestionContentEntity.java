package org.tutor.materials.textbook.model.entity.content.question;


import lombok.Getter;
import lombok.Setter;
import org.tutor.materials.textbook.model.entity.shared.TwoDimensionalListOfStringsConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public abstract class ClosedQuestionContentEntity extends QuestionContentEntity {

    @Column(name = "correct_answers")
    @Convert(converter = TwoDimensionalListOfStringsConverter.class)
    List<List<String>> correctAnswers = new ArrayList<>();

}
