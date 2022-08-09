package org.tutor.materials.textbook.model.entity.content.question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.textbook.model.entity.shared.StringArrayToStringConverter;
import org.tutor.materials.textbook.model.entity.shared.TwoDimensionalListOfStringsConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chooseaword_content")
@Getter @Setter @NoArgsConstructor
public class ChooseAWordContentEntity extends ClosedQuestionContentEntity {

    @Column(name = "word_choice")
    @Convert(converter = TwoDimensionalListOfStringsConverter.class)
    List<List<String>> wordChoice = new ArrayList<>();

    @Column(name = "content_parts")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> contentParts = new ArrayList<>();

    @Override
    public String generateId() {
        return "choose-a-word";
    }
}
