package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation;

import org.tutor.materials.model.entity.users.TeacherEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("open")
public class OpenQuestionEvaluationEntityEntity extends QuestionEvaluationEntity {

    @OneToOne
    TeacherEntity teacher;

    int rating;

    String correction;

}
