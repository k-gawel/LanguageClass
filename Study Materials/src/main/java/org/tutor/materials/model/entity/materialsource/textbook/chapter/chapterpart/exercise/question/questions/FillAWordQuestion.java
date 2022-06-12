package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.ClosedQuestionContent;
import org.tutor.materials.model.entity.utils.StringArrayToStringConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class FillAWordQuestion extends ClosedQuestionContent {

    @Column
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> contentParts = new ArrayList<>();

}
