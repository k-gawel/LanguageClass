package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.QuestionEvaluationEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercise_evaluation")
@Getter @Setter @NoArgsConstructor
public class ExerciseEvaluationEntity extends AbstractEntity {

    @OneToOne
    ExerciseAnswerEntity answer;

    @ManyToMany
    List<QuestionEvaluationEntity> questionEvaluations = new ArrayList<>();

    int rating;

}
