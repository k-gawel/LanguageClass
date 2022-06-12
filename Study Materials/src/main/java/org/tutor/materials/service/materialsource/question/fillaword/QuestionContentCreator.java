package org.tutor.materials.service.materialsource.question.fillaword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.tutor.materials.model.dto.input.fillaword.FillAWordQuestionInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.FillAWordQuestion;
import org.tutor.materials.repository.QuestionContentRepository;

import java.util.Objects;

@Service
class QuestionContentCreator {

    private final QuestionContentRepository contentRepository;

    @Autowired
    QuestionContentCreator(QuestionContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    FillAWordQuestion create(FillAWordQuestionInput input) {
        validateInput(input);
        var question = fromInput(input);
        return contentRepository.save(question);
    }

    private FillAWordQuestion fromInput(FillAWordQuestionInput input) {
        var question = new FillAWordQuestion();
        question.setContentParts(input.content());
        question.setCorrectAnswers(input.correctAnswers());
        return question;
    }

    private void validateInput(FillAWordQuestionInput input) {
        var blankSpaces = input.content().stream().filter(Objects::isNull).count();

        var doesSizesMatch = blankSpaces == input.correctAnswers().size();
        var areCorrectAnswersNotNull = input.correctAnswers().stream()
                .filter(CollectionUtils::isEmpty)
                .findAny()
                .isEmpty();

        if(!doesSizesMatch || !areCorrectAnswersNotNull)
            throw new IllegalArgumentException("Wrong input for fill a word question.");
    }
}
