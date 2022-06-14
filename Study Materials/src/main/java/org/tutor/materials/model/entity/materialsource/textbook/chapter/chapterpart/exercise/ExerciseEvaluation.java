package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.QuestionEvaluation;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class ExerciseEvaluation extends AbstractEntity {

    @OneToOne
    ExerciseAnswer answer;

    @ManyToMany
    List<QuestionEvaluation> questionEvaluations = new ArrayList<>();

    int rating;

}
