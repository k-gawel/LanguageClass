package org.tutor.materials.service.materialsource.question.chooseaword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.dto.input.chooseaword.ClosedQuestionAnswerInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.repository.ExerciseAnswerRepository;
import org.tutor.materials.repository.QuestionContentRepository;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
class ExerciseAnswerCreator {

    private final ExerciseAnswerRepository repository;
    private final QuestionAnswerCreator questionAnswerCreator;
    private final QuestionContentRepository questionContentRepository;

    @Autowired
    ExerciseAnswerCreator(ExerciseAnswerRepository repository, QuestionAnswerCreator questionAnswerCreator, QuestionContentRepository questionContentRepository) {
        this.repository = repository;
        this.questionAnswerCreator = questionAnswerCreator;
        this.questionContentRepository = questionContentRepository;
    }

    ExerciseAnswer answer(ExerciseContent exerciseContent, List<ClosedQuestionAnswerInput> answersInput) {
        var questionSize = questionContentRepository.getQuestionsSize(exerciseContent);

        if (answersInput == null || questionSize != answersInput.size())
            throw new IllegalArgumentException("Answers size must be equal to exercise questions size.");

        var answers = answersInput.stream().map(questionAnswerCreator::fromInput).toList();

        var result = new ExerciseAnswer();
        result.setExerciseContent(exerciseContent);
        result.setAnswers(answers);
        result.setCreatedAt(new Date(Calendar.getInstance().getTime().getTime()));

        return repository.save(result);
    }



}