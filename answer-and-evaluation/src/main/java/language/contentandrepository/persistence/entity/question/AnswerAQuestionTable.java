package language.contentandrepository.persistence.entity.question;

import javax.persistence.*;

@MappedSuperclass
@DiscriminatorValue("answer_a_question")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AnswerAQuestionTable extends QuestionContentEntity {
}
