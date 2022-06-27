package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.tutor.materials.model.entity.OrderedContentContainerEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.ChapterPartEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionType;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.QuestionContentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercise_content")
@NoArgsConstructor @Getter @Setter
public class ExerciseContentEntity extends ChapterPartEntity<QuestionContentEntity> {

    @NotNull
    QuestionType type;

    String title;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    List<QuestionContentEntity> content = new ArrayList<>();

    public ExerciseContentEntity(@NotNull QuestionType type, String title) {
        this.type = type;
        this.title = title;
    }

}
