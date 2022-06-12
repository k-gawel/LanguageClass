package org.tutor.materials.resolver.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluation;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.QuestionEvaluation;
import org.tutor.materials.repository.QuestionEvaluationRepository;

import java.util.List;

@Component
public class ExerciseEvaluationResolver implements GraphQLResolver<ExerciseEvaluation> {

    private final QuestionEvaluationRepository questionEvaluationRepository;

    @Autowired
    public ExerciseEvaluationResolver(QuestionEvaluationRepository questionEvaluationRepository) {
        this.questionEvaluationRepository = questionEvaluationRepository;
    }

    public List<QuestionEvaluation> questionEvaluations(ExerciseEvaluation evaluation) {
        return questionEvaluationRepository.findByExerciseEvaluation(evaluation);
    }

}
