package org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.OpenQuestionContentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "answer_a_question_question")
@Getter @Setter @NoArgsConstructor
public class AnswerAQuestionQuestionEntity extends OpenQuestionContentEntity {

    String question;

}
