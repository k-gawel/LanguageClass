package language.contentandrepository.persistence.entity.textbook;

import language.contentandrepository.persistence.entity.IdentifiableEntity;
import language.contentandrepository.persistence.entity.enums.ChapterContentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @Setter @NoArgsConstructor
public class ChapterContentEntity extends IdentifiableEntity {

    @Column(name = "chapter_content_type")
    private ChapterContentType chapterContentType;

}