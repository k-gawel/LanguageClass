package org.tutor.materials.model.dto.input;

import org.tutor.materials.model.questiontype.Question;

import java.util.List;

public interface ExerciseInput<Q extends Question> {

    Long chapterId();
    List<? extends QuestionInput<Q>> questions();
    Integer place();
    Class<Q> type();
    String title();

}
