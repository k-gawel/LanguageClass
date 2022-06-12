package org.tutor.materials.repository.question;

import org.springframework.stereotype.Repository;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.ClosedQuestionContent;
import org.tutor.materials.repository.BasicRepository;

@Repository
public interface ClosedQuestionRepository<Q extends ClosedQuestionContent> extends BasicRepository<Q> {

}
