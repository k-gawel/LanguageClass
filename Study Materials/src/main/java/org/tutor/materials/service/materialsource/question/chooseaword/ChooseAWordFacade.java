package org.tutor.materials.service.materialsource.question.chooseaword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
class ChooseAWordFacade implements ChooseAWordService {

    private final QuestionContentCreator questionContentCreator;
    private final ExerciseContentCreator exerciseContentCreator;

    private final QuestionAnswerCreator questionAnswerCreator;
    private final ExerciseAnswerCreator exerciseAnswerCreator;

    private final QuestionEvaluationCreator questionEvaluationCreator;
    private final ExerciseEvaluationCreator exerciseEvaluationCreator;

    @Autowired
    ChooseAWordFacade(QuestionContentCreator creator, ExerciseContentCreator exerciseCreator, QuestionAnswerCreator questionAnswerCreator, ExerciseAnswerCreator exerciseAnswerCreator, QuestionEvaluationCreator questionEvaluationCreator, ExerciseEvaluationCreator exerciseEvaluationCreator) {
        this.questionContentCreator = creator;
        this.exerciseContentCreator = exerciseCreator;
        this.questionAnswerCreator = questionAnswerCreator;
        this.exerciseAnswerCreator = exerciseAnswerCreator;
        this.questionEvaluationCreator = questionEvaluationCreator;
        this.exerciseEvaluationCreator = exerciseEvaluationCreator;
    }

    @Override
    public ChooseAWordContent createQuestion(ChooseAWordQuestionInput input, List<String> commonChoice) {
        return questionContentCreator.createQuestion(input, commonChoice);
    }

    @Override
    public ChooseAWordContent createQuestion(ChooseAWordQuestionInput input) {
        return questionContentCreator.createQuestion(input, null);
    }

    @Override
    public ExerciseContent createExercise(Chapter chapter, ChooseAWordExerciseInput input) {
        return exerciseContentCreator.createExercise(chapter, input);
    }

    @Override
    public QuestionAnswer answer(ChooseAWordContent question, List<String> answers) {
        return questionAnswerCreator.answer(question, answers);
    }

    @Override
    public ExerciseAnswer answer(ExerciseContent exerciseContent, List<ClosedQuestionAnswerInput> answers) {
        var answer = exerciseAnswerCreator.answer(exerciseContent, answers);
        evaluate(answer);
        return answer;
    }

    @Override
    public ClosedQuestionEvaluation evaluate(QuestionAnswer question) {
        return questionEvaluationCreator.evaluate(question);
    }

    @Override
    public ExerciseEvaluation evaluate(ExerciseAnswer exercise) {
        return exerciseEvaluationCreator.evaluate(exercise);
    }
}
