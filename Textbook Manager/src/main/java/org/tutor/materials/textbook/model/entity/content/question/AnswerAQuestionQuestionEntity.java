package org.tutor.materials.textbook.model.entity.content.question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "answer_a_question_question")
@Getter @Setter @NoArgsConstructor
public class AnswerAQuestionQuestionEntity extends OpenQuestionContentEntity {

    @Column(name = "question_content")
    private String question;

    @Override
    public String generateId() {
        return "answer-a-question-" + question.substring(0, Math.min(15, question.length() - 1));
    }

}
