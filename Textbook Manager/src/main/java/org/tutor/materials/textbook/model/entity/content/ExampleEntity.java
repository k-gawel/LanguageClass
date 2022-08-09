package org.tutor.materials.textbook.model.entity.content;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "example")
@Getter @Setter @NoArgsConstructor
public class ExampleEntity extends ChapterPartEntity {

    @Column(name = "title")
    private String title;

    @Override
    public List<java.util.UUID> getContentIds() {
        return null;
    }

    @Override
    public void setContentIds(List contentIds) {
    }

    @Override
    public List getContent() {
        return null;
    }

    @Override
    public void setContent(List content) {

    }

    @Override
    public String generateId() {
        return title;
    }
}
