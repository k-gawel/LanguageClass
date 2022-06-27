package org.tutor.materials.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.domain.ExerciseContent;
import org.tutor.materials.model.dto.input.ExerciseInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContentEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionType;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.QuestionContentEntity;
import org.tutor.materials.model.questiontype.Question;
import org.tutor.materials.repository.ExerciseContentRepository;

@Service
public class ExerciseContentManager extends OrderedContentContainerManager<ExerciseContentEntity, QuestionContentEntity> {

    private final ExerciseContentRepository repository;

    @Autowired
    public ExerciseContentManager(ExerciseContentRepository repository) {
        this.repository = repository;
    }


    ExerciseContentEntity fromInput(ExerciseInput input) {
        var entity = new ExerciseContentEntity();
        entity.setType(QuestionType.getByClass(input.type()));
        entity.setTitle(input.title());
        return entity;
    }

    <Q extends Question> ExerciseContent<Q> fromEntity(ExerciseContentEntity entity, Class<Q> type) {
        return new ExerciseContent<>(
                entity.getUUID(),
                type,
                entity.getTitle()
        );
    }

}
