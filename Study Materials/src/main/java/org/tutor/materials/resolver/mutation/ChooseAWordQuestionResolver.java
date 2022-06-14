package org.tutor.materials.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.dto.input.chooseaword.ChooseAWordExerciseInput;
import org.tutor.materials.model.dto.input.chooseaword.ChooseAWordQuestionInput;
import org.tutor.materials.model.dto.input.chooseaword.ClosedQuestionAnswerInput;
import org.tutor.materials.model.dto.output.ExerciseAnswerAndEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.ChooseAWordContent;
import org.tutor.materials.repository.BasicRepository;
import org.tutor.materials.repository.ChapterRepository;
import org.tutor.materials.repository.ExerciseEvaluationRepository;
import org.tutor.materials.service.materialsource.question.chooseaword.ChooseAWordService;

import java.util.List;

@Component
public class ChooseAWordQuestionResolver implements GraphQLMutationResolver {

    private final ChooseAWordService service;
    private final BasicRepository<ExerciseContent> exerciseRepository;
    private final ChapterRepository chapterRepository;

    @Autowired
    public ChooseAWordQuestionResolver(ChooseAWordService service, BasicRepository<ExerciseContent> exerciseRepository, ChapterRepository chapterRepository, ExerciseEvaluationRepository exerciseEvaluationRepository) {
        this.service = service;
        this.exerciseRepository = exerciseRepository;
        this.chapterRepository = chapterRepository;
    }

    public ChooseAWordContent createChooseAWordQuestion(String token, Integer exerciseId, ChooseAWordQuestionInput input) {
        var exercise = exerciseRepository.findById(Long.valueOf(exerciseId)).orElseThrow();
        var question = service.createQuestion(input);

        exercise.getQuestions().add(question);
        exerciseRepository.save(exercise);
        return question;
    }


    public ExerciseContent createChooseAWordExercise(String token, Long chapterId, ChooseAWordExerciseInput input) {
        var chapter = chapterRepository.findById(chapterId).orElseThrow();

        var exercise = service.createExercise(chapter, input);

        return exercise;
    }

}
