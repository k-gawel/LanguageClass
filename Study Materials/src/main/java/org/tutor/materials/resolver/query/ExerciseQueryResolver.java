package org.tutor.materials.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.dto.output.ExerciseAnswerAndEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.repository.ExerciseAnswerRepository;
import org.tutor.materials.repository.ExerciseEvaluationRepository;
import org.tutor.materials.repository.ExerciseContentRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;


@Component
public class ExerciseQueryResolver implements GraphQLQueryResolver {

    private final ExerciseContentRepository exerciseContentRepository;
    private final ExerciseEvaluationRepository evaluationRepository;
    private final ExerciseAnswerRepository answerRepository;



    @Autowired
    public ExerciseQueryResolver(ExerciseContentRepository repository, ExerciseEvaluationRepository evaluationRepository, ExerciseAnswerRepository answerRepository) {
        this.exerciseContentRepository = repository;
        this.evaluationRepository = evaluationRepository;
        this.answerRepository = answerRepository;
    }

    public ExerciseContent exerciseContent(Long id) {
        return exerciseContentRepository.findById(id).orElseThrow();
    }


}
