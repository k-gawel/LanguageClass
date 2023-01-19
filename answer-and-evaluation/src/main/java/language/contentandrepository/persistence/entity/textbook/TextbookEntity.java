package language.contentandrepository.persistence.entity.textbook;

import language.contentandrepository.persistence.entity.user.StudentEntity;
import language.contentandrepository.persistence.entity.user.TeacherEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "textbook")
public class TextbookEntity extends TextbookTable {

    @Column(name = "title")
    private String title;

    @Column(name = "is_public")
    private boolean isPublic = false;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "textbook_allowed_users",
            joinColumns = @JoinColumn(name = "textbook"),
            inverseJoinColumns = @JoinColumn(name = "student")
    )
    private Set<StudentEntity.ID> allowedUsers = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @JoinTable(
            name = "textbook_chapter",
            joinColumns = @JoinColumn(name = "textbook"),
            inverseJoinColumns = @JoinColumn(name = "chapter")
    )
    private List<ChapterEntity.ID> chapters;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private TeacherEntity.ID author;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Entity
    @Table(name = "textbook")
    @Getter @Setter @NoArgsConstructor
    public static class ID extends TextbookTable {
    }

}
