package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation;

import org.tutor.materials.model.entity.users.Teacher;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("open")
public class OpenQuestionEvaluation extends QuestionEvaluation {

    @OneToOne
    Teacher teacher;

    int rating;

    String correction;

}
