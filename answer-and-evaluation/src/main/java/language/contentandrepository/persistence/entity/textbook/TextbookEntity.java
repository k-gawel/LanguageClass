package language.contentandrepository.persistence.entity.textbook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import language.contentandrepository.persistence.entity.user.StudentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "textbook")
public class TextbookEntity extends TextbookTable {

    @Column(name = "title")
    private String title;

    @Column(name = "is_public")
    private boolean isPublic = false;

    @OneToMany
    @JoinTable(
            name = "textbook_allowed_users",
            joinColumns = @JoinColumn(name = "textbook"),
            inverseJoinColumns = @JoinColumn(name = "student")
    )
    private List<StudentEntity.ID> allowedUsers = new ArrayList<>();

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @JoinTable(
            name = "textbook_chapter",
            joinColumns = @JoinColumn(name = "textbook"),
            inverseJoinColumns = @JoinColumn(name = "chapter")
    )
    private List<ChapterEntity.ID> chapters;

    @Entity
    @Table(name = "textbook")
    @Getter @Setter @NoArgsConstructor
    public static class ID extends TextbookTable {
    }

}
