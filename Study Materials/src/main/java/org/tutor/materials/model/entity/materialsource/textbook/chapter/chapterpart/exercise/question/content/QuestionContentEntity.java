package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content;

import org.tutor.materials.model.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class QuestionContentEntity extends AbstractEntity {

}
