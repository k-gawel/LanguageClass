package org.tutor.materials.textbook.model.entity.content;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.textbook.model.entity.shared.OrderedContentContainerEntity;
import org.tutor.materials.textbook.model.entity.users.StudentEntity;
import org.tutor.materials.textbook.model.entity.users.TeacherEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "textbook")
@Getter @Setter @NoArgsConstructor
public class TextbookEntity extends OrderedContentContainerEntity<ChapterEntity> {

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "created_by")
    private TeacherEntity createdBy;


    @JoinTable(name = "textbook_chapter",
               joinColumns = @JoinColumn(name = "textbook"),
               inverseJoinColumns = @JoinColumn(name="chapter", referencedColumnName = "key")
    )
    @OneToMany
    List<ChapterEntity> content = new ArrayList<>();

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(
//            name = "textbook_chapter",
//            joinColumns = @JoinColumn(name = "textbook")
//    )
//    private List<String> contentIds = new ArrayList<>();


    @Override
    public List<String> getContentIds() {
        return null;
    }

    @Override
    public void setContentIds(List<String> contentIds) {

    }

    private boolean isPublic;

    @OneToMany
    @JoinTable(
            name = "textbook_allowed_users",
            joinColumns = @JoinColumn(name = "textbook", unique = false),
            inverseJoinColumns = @JoinColumn(name = "student", unique = false)
    )
    private List<StudentEntity> users;

    @Override
    public String generateId() {
        var titleId = generateIdFromString(this.getTitle());
        return this.getCreatedBy().getId() + "_" + titleId + "_" + (this.isPublic() ? "public" : "private");
    }

}
