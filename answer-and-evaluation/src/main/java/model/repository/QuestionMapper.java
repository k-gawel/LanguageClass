package model.repository;

import model.domain.ChooseAWordQuestion;
import model.domain.ID;
import model.domain.Question;
import model.repository.utils.Converter;
import org.springframework.jdbc.core.RowMapper;

public final class QuestionMapper {

    private QuestionMapper() {}

    public static RowMapper<ChooseAWordQuestion> ChooseAWord = (r, i) -> {
        var id = new ID<>(ChooseAWordQuestion.class, r.getString("id"));
        var correctAnswersString = r.getString("correct_answers");

        return new ChooseAWordQuestion(
                id,
                Converter.FromDatabase.nestedStringList(correctAnswersString)
        );
    };

}
