package language.contentandrepository.persistence.entity.question;

import language.contentandrepository.persistence.entity.utils.StringListConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class AnswerAQuestionEntity extends AnswerAQuestionTable {

    @Column(insertable = false, updatable = false)
    private String type = "answer_a_question";

    @Convert(converter = StringListConverter.class)
    @Column(name = "content_parts")
    private List<String> questionContent;

    @Entity
    @DiscriminatorValue("answer_a_question")
    @Getter @Setter @NoArgsConstructor
    public static class ID extends AnswerAQuestionTable {
    }

}
