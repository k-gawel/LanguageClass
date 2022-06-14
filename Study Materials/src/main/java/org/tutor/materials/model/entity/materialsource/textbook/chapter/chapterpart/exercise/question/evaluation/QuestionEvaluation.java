package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswer;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorColumn(name = "question_type")
@Getter @Setter @NoArgsConstructor
public abstract class QuestionEvaluation extends AbstractEntity {

    @OneToOne
    QuestionAnswer answer;

}
