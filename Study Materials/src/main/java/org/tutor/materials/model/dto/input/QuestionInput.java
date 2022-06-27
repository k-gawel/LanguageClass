package org.tutor.materials.model.dto.input;

import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionType;
import org.tutor.materials.model.questiontype.Question;

public interface QuestionInput<Q extends Question> {

    Class<Q> type();

}
