package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.ChapterPartEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "example")
@Getter @Setter @NoArgsConstructor
public class ExampleEntity extends ChapterPartEntity {

    String title;

    @Override
    public List getContent() {
        return null;
    }

    @Override
    public void setContent(List content) {

    }

}
