package org.tutor.materials.model.domain.interfaces;

import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionType;
import org.tutor.materials.model.questiontype.Question;

public interface QuestionContent<Q extends Question> extends Identifiable{

    Class<Q> type();

}
