package org.tutor.materials.model.entity;

import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public abstract class OrderedContentContainerEntity<CHILD extends AbstractEntity> extends AbstractEntity {

     public abstract List<CHILD> getContent();

     public abstract void setContent(List<CHILD> content);

}
