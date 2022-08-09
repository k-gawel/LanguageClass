package org.tutor.materials.textbook.model.entity.content;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.textbook.model.entity.shared.OrderedContentContainerEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chapter")
@Getter @Setter @NoArgsConstructor
public class ChapterEntity extends OrderedContentContainerEntity<ChapterPartEntity> {

    @Column(name = "title")
    private String title;

    @OneToMany
    @JoinTable(
            name = "chapter_content",
            joinColumns = @JoinColumn(name = "chapter"),
            inverseJoinColumns = @JoinColumn(name = "content")
    )
    private List<ChapterPartEntity> content = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "chapter_content", joinColumns = @JoinColumn(name = "chapter"))
    @Column(name = "content")
    private List<String> contentIds;

    @Override
    public String generateId() {
        return generateIdFromString(title);
    }

}
