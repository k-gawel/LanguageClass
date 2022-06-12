package org.tutor.materials.service.materialsource.question.fillaword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.dto.input.fillaword.FillAWordExerciseInput;
import org.tutor.materials.model.dto.input.fillaword.FillAWordQuestionInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionType;
import org.tutor.materials.repository.ExerciseContentRepository;
import org.tutor.materials.service.materialsource.chapter.ChapterService;

import java.util.List;

@Service
class ExerciseContentCreator {

    private final ExerciseContentRepository contentRepository;
    private final ChapterService chapterService;
    private final QuestionContentCreator questionContentCreator;

    @Autowired
    ExerciseContentCreator(ExerciseContentRepository contentRepository, ChapterService chapterService, QuestionContentCreator questionContentCreator) {
        this.contentRepository = contentRepository;
        this.chapterService = chapterService;
        this.questionContentCreator = questionContentCreator;
    }

    ExerciseContent create(Chapter chapter, FillAWordExerciseInput input) {
        var exercise = fromInput(input);
        exercise = contentRepository.save(exercise);
        chapterService.addChapterPart(chapter, exercise, null);
        return exercise;
    }

    private ExerciseContent fromInput(FillAWordExerciseInput input) {
        var exercise = new ExerciseContent();

        exercise.setType(QuestionType.FILL_A_WORD);
        setQuestions(exercise, input.questions());
        exercise.setTitle(input.title());

        return exercise;
    }

    private void setQuestions(ExerciseContent exercise, List<FillAWordQuestionInput> questionInputs) {
        var questionList = exercise.getQuestions();
        questionInputs.stream()
                .map(questionContentCreator::create)
                .forEach(questionList::add);
    }

}
