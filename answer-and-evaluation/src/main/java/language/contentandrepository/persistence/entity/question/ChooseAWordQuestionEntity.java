package language.contentandrepository.persistence.entity.question;

import language.contentandrepository.persistence.entity.utils.NestedStringListConverter;
import language.contentandrepository.persistence.entity.utils.StringListConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue(value = "choose_a_word")
@Getter @Setter @NoArgsConstructor
public class ChooseAWordQuestionEntity extends QuestionContentEntity {

    @Column(name = "correct_answers")
    @Convert(converter = NestedStringListConverter.class)
    private List<List<String>> correctAnswers;

    @Column(name = "content_parts")
    @Convert(converter = StringListConverter.class)
    private List<String> contentParts;

    @Column(name = "word_choice")
    @Convert(converter = NestedStringListConverter.class)
    private List<List<String>> wordChoice;

    @Entity
    @Table(name = "question_content")
    @DiscriminatorValue(value = "choose_a_word")
    @Getter @Setter @NoArgsConstructor
    public static class ID extends QuestionContentEntity.ID {
    }

}
