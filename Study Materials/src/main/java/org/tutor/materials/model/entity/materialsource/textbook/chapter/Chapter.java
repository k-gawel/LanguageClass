package org.tutor.materials.model.entity.materialsource.textbook.chapter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.ChapterPart;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Chapter extends AbstractEntity {

    String title;

    @OneToMany
    List<ChapterPart> content = new ArrayList<>();

}
