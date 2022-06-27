package org.tutor.materials.repository.question;

import org.springframework.stereotype.Repository;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.ChooseAWordContentEntity;

@Repository
public interface ChooseAWordQuestionRepository extends ClosedQuestionRepository<ChooseAWordContentEntity> {


}
