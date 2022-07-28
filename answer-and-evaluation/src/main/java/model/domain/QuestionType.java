package model.domain;

import java.util.Arrays;

public enum QuestionType {

    CHOOSE_A_WORD("choose_a_word", ChooseAWordQuestion.class),
    FILL_A_WORD("fill_a_word", Question.class);

    private final String stringValue;
    private final Class<? extends Question> questionClass;

    QuestionType(String stringValue, Class<? extends Question> questionClass) {
        this.stringValue = stringValue;
        this.questionClass = questionClass;
    }

    public static QuestionType fromString(String stringValue) {
        return Arrays.stream(values())
                .filter(e -> e.stringValue.equals(stringValue))
                .findFirst().orElseThrow();
    }

    public Class<? extends Question> questionClass() {
        return questionClass;
    }

}
