package org.tutor.materials.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.dto.input.chooseaword.ClosedQuestionAnswerInput;
import org.tutor.materials.model.dto.input.chooseaword.ClosedQuestionsExerciseAnswerInput;
import org.tutor.materials.model.dto.output.ExerciseAnswerAndEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluation;
import org.tutor.materials.repository.BasicRepository;
import org.tutor.materials.repository.ChapterRepository;
import org.tutor.materials.repository.ExerciseEvaluationRepository;
import org.tutor.materials.service.materialsource.question.ClosedQuestionService;
import org.tutor.materials.service.materialsource.question.chooseaword.ChooseAWordService;
import org.tutor.materials.service.materialsource.question.fillaword.FillAWordService;

import java.util.List;
import java.util.function.BiFunction;

@Component
public class AnswerResolver implements GraphQLMutationResolver {

    private final ChooseAWordService chooseAWordService;
    private final FillAWordService fillAWordService;
    private final BasicRepository<ExerciseContent> exerciseRepository;
    private final ExerciseEvaluationRepository exerciseEvaluationRepository;

    @Autowired
    public AnswerResolver(ChooseAWordService service, BasicRepository<ExerciseContent> exerciseRepository, ChapterRepository chapterRepository, FillAWordService fillAWordService, ExerciseEvaluationRepository exerciseEvaluationRepository) {
        this.chooseAWordService = service;
        this.exerciseRepository = exerciseRepository;
        this.fillAWordService = fillAWordService;
        this.exerciseEvaluationRepository = exerciseEvaluationRepository;
    }

    public ExerciseAnswerAndEvaluation answerClosedQuestionExercise(Long exerciseId, List<ClosedQuestionAnswerInput> answers) {
        var exercise = exerciseRepository.findById(exerciseId).orElseThrow();

        ClosedQuestionService service = switch (exercise.getType()) {
            case CHOOSE_A_WORD -> chooseAWordService;
            case FILL_A_WORD -> fillAWordService;
        };

        var exerciseAnswer = service.answer(exercise, answers);
        var exerciseEvaluation = exerciseEvaluationRepository.findByAnswer(exerciseAnswer).orElse(null);


        return new ExerciseAnswerAndEvaluation(exerciseAnswer, exerciseEvaluation);
    }

}
