package org.tutor.materials.textbook.model.entity.content;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.textbook.model.entity.shared.AbstractEntity;
import org.tutor.materials.textbook.model.entity.shared.OrderedContentContainerEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @Setter @NoArgsConstructor
public abstract class ChapterPartEntity<Q extends AbstractEntity> extends OrderedContentContainerEntity<Q> {

}
