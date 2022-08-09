package org.tutor.materials.textbook.model.entity.shared;

import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public abstract class OrderedContentContainerEntity<CHILD extends AbstractEntity> extends AbstractEntity {

     public abstract List<CHILD> getContent();
     public abstract void setContent(List<CHILD> content);
     public abstract List<String> getContentIds();
     public abstract void setContentIds(List<String> contentIds);

}
