package org.tutor.materials.model.entity.materialsource.textbook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.OrderedContentContainerEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.ChapterEntity;
import org.tutor.materials.model.entity.users.TeacherEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "textbook")
@Getter @Setter @NoArgsConstructor
public class TextbookEntity extends OrderedContentContainerEntity<ChapterEntity> {

    String name;

    @OneToOne
    TeacherEntity createdBy;

    @JoinTable(name = "textbook_chapter",
               joinColumns = @JoinColumn(name = "textbook_id"),
               inverseJoinColumns = @JoinColumn(name="chapter_id")
    )
    @OneToMany
    List<ChapterEntity> content = new ArrayList<>();

}
