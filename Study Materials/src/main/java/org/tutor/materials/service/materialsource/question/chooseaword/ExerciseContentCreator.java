package org.tutor.materials.service.materialsource.question.chooseaword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.dto.input.chooseaword.ChooseAWordExerciseInput;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionType;
import org.tutor.materials.repository.BasicRepository;
import org.tutor.materials.service.materialsource.chapter.ChapterService;

@Service
class ExerciseContentCreator {

    private final BasicRepository<AbstractEntity> basicRepository;
    private final QuestionContentCreator questionService;
    private final ChapterService chapterService;

    @Autowired
    public ExerciseContentCreator(BasicRepository<AbstractEntity> basicRepository, QuestionContentCreator questionService, QuestionAnswerCreator answerService, ChapterService chapterService, ExerciseAnswerCreator exerciseAnswerCreator) {
        this.basicRepository = basicRepository;
        this.questionService = questionService;
        this.chapterService = chapterService;
    }

    ExerciseContent createExercise(Chapter chapter, ChooseAWordExerciseInput input) {
        var exercise = fromInput(input);
        chapterService.addChapterPart(chapter, exercise, input.number());
        return exercise;
    }

    ExerciseContent fromInput(ChooseAWordExerciseInput input) {
        var exercise = new ExerciseContent();

        exercise.setTitle(input.title());
        exercise.setType(QuestionType.CHOOSE_A_WORD);
        setQuestions(input, exercise);

        return basicRepository.save(exercise);
    }
    
    private void setQuestions(ChooseAWordExerciseInput input, ExerciseContent exerciseContent) {
        input.questions().stream()
                .map(i -> questionService.createQuestion(i, input.commonChoice()))
                .forEach(q -> exerciseContent.getQuestions().add(q));
    }



}
