package language.contentandrepository.persistence.entity.textbook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chapter")
@Getter @Setter @NoArgsConstructor
public class ChapterEntity extends ChapterTable {

    private String title;


    @OneToMany
    @JoinTable(
            name = "chapter_content",
            joinColumns = @JoinColumn(name = "chapter"),
            inverseJoinColumns = @JoinColumn(name = "content")
    )
    private List<ChapterContentEntity> content = new java.util.ArrayList<>();

    @Entity
    @Table(name = "chapter")
    @Getter @Setter @NoArgsConstructor
    public static class ID extends ChapterTable {
    }

}
