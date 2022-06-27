package org.tutor.materials.repository.question;

import org.springframework.stereotype.Repository;
import org.tutor.materials.model.domain.interfaces.QuestionContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.ClosedQuestionContentEntity;
import org.tutor.materials.repository.BasicRepository;

@Repository
public interface ClosedQuestionRepository<Q extends ClosedQuestionContentEntity> extends BasicRepository<Q, QuestionContent> {

}
