package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.ChapterPart;

import javax.persistence.Entity;

@Entity
@Getter @Setter @NoArgsConstructor
public class Example extends ChapterPart {

    String title;

}
