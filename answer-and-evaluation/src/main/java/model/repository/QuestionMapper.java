package model.repository;

import model.domain.ChooseAWordQuestion;
import model.domain.ID;
import model.domain.Question;
import model.repository.utils.Converter;
import org.springframework.jdbc.core.RowMapper;

public final class QuestionMapper {

    private QuestionMapper() {}

    public static RowMapper<ChooseAWordQuestion> ChooseAWord = (r, i) -> {
        var wordChoiceString = r.getString("word_choice");
        var id = new ID<>(ChooseAWordQuestion.class, r.getString("id"));
        var correctAnswersString = r.getString("correct_answers");
        var contentPartsString = r.getString("content_parts");

        return new ChooseAWordQuestion(
                id,
                Converter.FromDatabase.nestedStringList(correctAnswersString)
        );
    };

}
