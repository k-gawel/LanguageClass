package org.tutor.materials.textbook.model.entity.content;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.tutor.materials.textbook.model.entity.content.question.QuestionContentEntity;
import org.tutor.materials.textbook.model.entity.shared.QuestionType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercise_content")
@NoArgsConstructor @Getter @Setter
public class ExerciseContentEntity extends ChapterPartEntity<QuestionContentEntity> {

    @NotNull

    private QuestionType type;

    @Column(name = "title")
    private String title;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "exercise_content_questions",
            joinColumns = @JoinColumn(name = "exercise"),
            inverseJoinColumns = @JoinColumn(name = "question")
    )
    private List<QuestionContentEntity> content = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "exercise_content_questions", joinColumns = @JoinColumn(name = "exercise"))
    @Column(name = "question")
    private List<String> contentIds;

    @Override
    public String generateId() {
        return null;
    }
}
