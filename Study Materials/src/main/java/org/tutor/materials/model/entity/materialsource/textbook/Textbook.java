package org.tutor.materials.model.entity.materialsource.textbook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.model.entity.users.Teacher;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Textbook extends AbstractEntity {

    String name;

    @OneToOne
    Teacher createdBy;

    @OneToMany
    List<Chapter> chapters = new ArrayList<>();

}
