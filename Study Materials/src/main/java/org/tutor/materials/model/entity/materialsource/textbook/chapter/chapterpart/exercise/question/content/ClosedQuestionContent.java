package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content;


import lombok.Getter;
import lombok.Setter;
import org.tutor.materials.model.entity.utils.TwoDimensionalListOfStringsConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public abstract class ClosedQuestionContent extends QuestionContent {

    @Column
    @Convert(converter = TwoDimensionalListOfStringsConverter.class)
    List<List<String>> correctAnswers = new ArrayList<>();

}
