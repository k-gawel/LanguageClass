package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswerEntity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorColumn(name = "question_type")
@Getter @Setter @NoArgsConstructor
public abstract class QuestionEvaluationEntity extends AbstractEntity {

    @OneToOne
    QuestionAnswerEntity answer;

}
