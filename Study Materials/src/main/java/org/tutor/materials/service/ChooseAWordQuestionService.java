package org.tutor.materials.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.domain.ChooseAWordQuestionContent;
import org.tutor.materials.model.dto.input.chooseaword.ChooseAWordQuestionInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.ChooseAWordContentEntity;
import org.tutor.materials.repository.QuestionContentRepository;

import javax.validation.ValidationException;

@Service
public class ChooseAWordQuestionService {

    private final QuestionContentRepository repository;

    @Autowired
    public ChooseAWordQuestionService(QuestionContentRepository repository) {
        this.repository = repository;
    }

    public ChooseAWordQuestionContent create(ChooseAWordQuestionInput input) throws ValidationException {
        input.validate();
        var entity = fromInput(input);
        repository.save(entity);
        var question = fromEntity(entity);
        return question;
    }

    ChooseAWordContentEntity fromInput(ChooseAWordQuestionInput input) {
        var entity = new ChooseAWordContentEntity();
        entity.setWordChoice(input.wordChoice());
        entity.setContentParts(input.content());
        entity.setCorrectAnswers(input.correctAnswers());
        return entity;
    }

    ChooseAWordQuestionContent fromEntity(ChooseAWordContentEntity entity) {
        return new ChooseAWordQuestionContent(
                entity.getUUID(),
                entity.getContentParts(),
                entity.getWordChoice(),
                entity.getCorrectAnswers()
        );
    }

}
