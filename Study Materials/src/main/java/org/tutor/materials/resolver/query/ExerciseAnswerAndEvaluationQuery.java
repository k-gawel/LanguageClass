package org.tutor.materials.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.dto.output.ExerciseAnswerAndEvaluation;
import org.tutor.materials.repository.ExerciseAnswerRepository;
import org.tutor.materials.repository.ExerciseEvaluationRepository;
import org.tutor.materials.repository.ExerciseContentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExerciseAnswerAndEvaluationQuery implements GraphQLQueryResolver {

    private final ExerciseContentRepository contentRepository;
    private final ExerciseAnswerRepository answerRepository;
    private final ExerciseEvaluationRepository evaluationRepository;

    @Autowired
    public ExerciseAnswerAndEvaluationQuery(ExerciseContentRepository contentRepository, ExerciseAnswerRepository answerRepository, ExerciseEvaluationRepository evaluationRepository) {
        this.contentRepository = contentRepository;
        this.answerRepository = answerRepository;
        this.evaluationRepository = evaluationRepository;
    }

    public ExerciseAnswerAndEvaluation getExerciseAnswerAndEvaluation(Long answerId) {
        var answer = answerRepository.findById(answerId).orElseThrow();
        var evaluation = evaluationRepository.findByAnswer(answer).orElse(null);

        return new ExerciseAnswerAndEvaluation(answer, evaluation);
    }

    public List<ExerciseAnswerAndEvaluation> getExerciseAnswersAndEvaluations(Long exerciseId) {
        var content = contentRepository.findById(exerciseId).orElseThrow();

        var answers = answerRepository.findExerciseAnswersByExerciseContent(content);

        return answers.stream().map((a) -> {
            var evaluation = evaluationRepository.findByAnswer(a).orElse(null);
            return new ExerciseAnswerAndEvaluation(a, evaluation);
        }).collect(Collectors.toList());
    }

}
