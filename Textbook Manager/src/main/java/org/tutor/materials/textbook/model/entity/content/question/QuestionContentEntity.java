package org.tutor.materials.textbook.model.entity.content.question;

import org.tutor.materials.textbook.model.entity.shared.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class QuestionContentEntity extends AbstractEntity {

}
