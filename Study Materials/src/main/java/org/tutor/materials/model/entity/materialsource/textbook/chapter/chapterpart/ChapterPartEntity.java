package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.OrderedContentContainerEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @Setter @NoArgsConstructor
public abstract class ChapterPartEntity<Q extends AbstractEntity> extends OrderedContentContainerEntity<Q> {

    String title;

}
