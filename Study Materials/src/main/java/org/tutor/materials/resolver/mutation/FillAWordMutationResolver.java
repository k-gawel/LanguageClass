package org.tutor.materials.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.dto.input.chooseaword.ClosedQuestionAnswerInput;
import org.tutor.materials.model.dto.input.fillaword.FillAWordExerciseInput;
import org.tutor.materials.model.dto.input.fillaword.FillAWordQuestionInput;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.FillAWordQuestion;
import org.tutor.materials.repository.ChapterRepository;
import org.tutor.materials.service.materialsource.question.fillaword.FillAWordService;

import java.util.List;

@Component
public class FillAWordMutationResolver implements GraphQLMutationResolver {

    private final FillAWordService service;
    private final ChapterRepository chapterRepository;

    @Autowired
    public FillAWordMutationResolver(FillAWordService service, ChapterRepository chapterRepository) {
        this.service = service;
        this.chapterRepository = chapterRepository;
    }

    public ExerciseContent createFillAWordExercise(Long chapterId, FillAWordExerciseInput input, Integer place) {
        var chapter = chapterRepository.findById(chapterId).orElseThrow();
        return service.createExercise(chapter, input);
    }

    public FillAWordQuestion createFillAWordQuestion(Long exerciseId, FillAWordQuestionInput input, Integer place) {
        return null;
    }

    ExerciseAnswer answerFillAWordExercise(Long exerciseId, List<ClosedQuestionAnswerInput> inputs) {
        return null;
    }

}
