package org.tutor.materials.service.materialsource.question.chooseaword;

import org.tutor.materials.model.dto.input.chooseaword.ChooseAWordExerciseInput;
import org.tutor.materials.model.dto.input.chooseaword.ChooseAWordQuestionInput;
import org.tutor.materials.model.dto.input.chooseaword.ClosedQuestionAnswerInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.ClosedQuestionEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.ChooseAWordContent;

import java.util.List;

public interface ChooseAWordService {

    ChooseAWordContent createQuestion(ChooseAWordQuestionInput input, List<String> commonChoice);
    ChooseAWordContent createQuestion(ChooseAWordQuestionInput input);

    ExerciseContent createExercise(Chapter chapter, ChooseAWordExerciseInput input);

    QuestionAnswer answer(ChooseAWordContent question, List<String> answers);
    ExerciseAnswer answer(ExerciseContent exerciseContent, List<ClosedQuestionAnswerInput> answers);

    ClosedQuestionEvaluation evaluate(QuestionAnswer question);
    ExerciseEvaluation evaluate(ExerciseAnswer exercise);
}
