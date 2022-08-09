package org.tutor.materials.textbook.model.entity.content.question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.textbook.model.entity.shared.StringArrayToStringConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fillaword_content")
@Getter @Setter @NoArgsConstructor
public class FillAWordQuestionEntity extends ClosedQuestionContentEntity {

    @Column(name = "content")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> content = new ArrayList<>();

    @Override
    public String generateId() {
        return "fill-a-word";
    }


}
