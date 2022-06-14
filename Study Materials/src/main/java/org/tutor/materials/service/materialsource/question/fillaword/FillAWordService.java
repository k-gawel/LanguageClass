package org.tutor.materials.service.materialsource.question.fillaword;

import org.tutor.materials.model.dto.input.chooseaword.ClosedQuestionAnswerInput;
import org.tutor.materials.model.dto.input.fillaword.FillAWordExerciseInput;
import org.tutor.materials.model.dto.input.fillaword.FillAWordQuestionInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.ClosedQuestionEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.FillAWordQuestion;
import org.tutor.materials.service.materialsource.question.ClosedQuestionService;

import java.util.List;

public interface FillAWordService extends ClosedQuestionService {

    FillAWordQuestion createQuestion(FillAWordQuestionInput input);
    ExerciseContent createExercise(Chapter chapter, FillAWordExerciseInput inputs);

    QuestionAnswer answer(FillAWordQuestion question, ClosedQuestionAnswerInput input);

    ClosedQuestionEvaluation evaluate(QuestionAnswer questionAnswer);
    ExerciseEvaluation evaluate(ExerciseAnswer exerciseAnswer);

}
