package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.ChapterPart;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionType;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.QuestionContent;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @Getter @Setter
public class ExerciseContent extends ChapterPart {

    @NotNull
    QuestionType type;

    String title;

    @OneToMany(fetch = FetchType.LAZY)
    List<QuestionContent> questions = new ArrayList<>();

}
