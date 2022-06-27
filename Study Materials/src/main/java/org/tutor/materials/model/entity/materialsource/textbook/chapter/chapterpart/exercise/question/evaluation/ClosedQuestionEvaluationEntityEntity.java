package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.utils.BooleanListToStringConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("closed")
@Getter @Setter @NoArgsConstructor
public class ClosedQuestionEvaluationEntityEntity extends QuestionEvaluationEntity {

    @Column
    @Convert(converter = BooleanListToStringConverter.class)
    List<Boolean> areAnswersCorrect;

}
