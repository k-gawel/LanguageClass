package org.tutor.materials.service.materialsource.question.fillaword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import org.tutor.materials.service.materialsource.question.ClosedEvaluationCreator;

import java.util.List;

@Service
class FillAWordFacade implements FillAWordService {

    private final QuestionContentCreator questionContentCreator;
    private final QuestionAnswerCreator questionAnswerCreator;
    private final QuestionEvaluationCreator questionEvaluationCreator;
    private final ExerciseContentCreator exerciseContentCreator;
    private final ExerciseAnswerCreator exerciseAnswerCreator;
    private final ClosedEvaluationCreator evaluationCreator;

    @Autowired
    FillAWordFacade(QuestionContentCreator questionContentCreator, QuestionAnswerCreator questionAnswerCreator, ExerciseContentCreator exerciseCreator, ExerciseAnswerCreator exerciseAnswerCreator, QuestionEvaluationCreator evaluationCreator, ClosedEvaluationCreator evaluationCreator1) {
        this.questionContentCreator = questionContentCreator;
        this.questionAnswerCreator = questionAnswerCreator;
        this.exerciseContentCreator = exerciseCreator;
        this.exerciseAnswerCreator = exerciseAnswerCreator;
        this.questionEvaluationCreator = evaluationCreator;
        this.evaluationCreator = evaluationCreator1;
    }

    @Override
    public FillAWordQuestion createQuestion(FillAWordQuestionInput input) {
        return this.questionContentCreator.create(input);
    }

    @Override
    public ExerciseContent createExercise(Chapter chapter, FillAWordExerciseInput input) {
        return this.exerciseContentCreator.create(chapter, input);
    }

    @Override
    public QuestionAnswer answer(FillAWordQuestion question, ClosedQuestionAnswerInput input) {
        return this.questionAnswerCreator.answer(question, input);
    }

    @Override
    public ExerciseAnswer answer(ExerciseContent exercise, List<ClosedQuestionAnswerInput> answers) {
        return this.exerciseAnswerCreator.answer(exercise, answers);
    }

    @Override
    public ClosedQuestionEvaluation evaluate(QuestionAnswer questionAnswer) {
        return this.questionEvaluationCreator.evaluate(questionAnswer);
    }

    @Override
    public ExerciseEvaluation evaluate(ExerciseAnswer exerciseAnswer) {
        return this.evaluationCreator.evaluate(exerciseAnswer);
    }
}
