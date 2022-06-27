package org.tutor.materials.model.entity.materialsource.textbook.chapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.OrderedContentContainerEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.ChapterPartEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chapter")
@Getter @Setter @NoArgsConstructor
public class ChapterEntity extends OrderedContentContainerEntity<ChapterPartEntity> {

    private String title;

    @OneToMany
    @JoinTable(
            name = "chapter_content",
            joinColumns = @JoinColumn(name = "chapter_uuid"),
            inverseJoinColumns = @JoinColumn(name = "content_uuid")
    )
    private List<ChapterPartEntity> content = new ArrayList<>();

    public ChapterEntity(String title) {
        this.title = title;
    }
}
