package org.tutor.materials.service.materialsource.question.fillaword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.dto.input.chooseaword.ClosedQuestionAnswerInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionType;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.FillAWordQuestion;
import org.tutor.materials.repository.ExerciseAnswerRepository;
import org.tutor.materials.repository.QuestionContentRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
class ExerciseAnswerCreator {

    private final QuestionAnswerCreator questionAnswerCreator;
    private final QuestionContentRepository questionContentRepository;
    private final ExerciseAnswerRepository exerciseAnswerRepository;

    @Autowired
    ExerciseAnswerCreator(QuestionAnswerCreator questionAnswerCreator, QuestionContentRepository questionContentRepository, ExerciseAnswerRepository exerciseAnswerRepository) {
        this.questionAnswerCreator = questionAnswerCreator;
        this.questionContentRepository = questionContentRepository;
        this.exerciseAnswerRepository = exerciseAnswerRepository;
    }

    ExerciseAnswer answer(ExerciseContent exerciseContent, List<ClosedQuestionAnswerInput> answers) {
        if(!exerciseContent.getType().equals(QuestionType.FILL_A_WORD))
            throw new IllegalArgumentException("Wrong exercise type. Expected: FILL_A_WORD");

        var answer = getAnswer(exerciseContent, answers);
        answer = exerciseAnswerRepository.save(answer);
        return answer;
    }

    private ExerciseAnswer getAnswer(ExerciseContent exercise, List<ClosedQuestionAnswerInput> inputs) {
        var answers = getQuestionAnswers(exercise, inputs);

        var exerciseAnswer = new ExerciseAnswer();
        exerciseAnswer.setExerciseContent(exercise);
        exerciseAnswer.setCreatedAt(new Date(System.currentTimeMillis()));
        exerciseAnswer.setAnswers(answers);
        return exerciseAnswer;
    }

    private List<QuestionAnswer> getQuestionAnswers(ExerciseContent exerciseContent, List<ClosedQuestionAnswerInput> inputs) {
        var result = new ArrayList<QuestionAnswer>();

        var questions = questionContentRepository.getByExercise(exerciseContent);

        for (ClosedQuestionAnswerInput input : inputs) {
            var answer = questions.stream()
                    .filter(q -> q.getId().equals(input.questionId())).findFirst()
                    .map(q -> (FillAWordQuestion) q)
                    .map(q -> questionAnswerCreator.answer(q, input))
                    .orElseThrow();
            result.add(answer);
        }
        return result;
    }
}
