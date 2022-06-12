package org.tutor.materials.resolver.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswer;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswer;
import org.tutor.materials.repository.QuestionAnswerRepository;

import java.util.List;

@Component
public class ExerciseAnswerResolver implements GraphQLResolver<ExerciseAnswer> {

    private final QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    public ExerciseAnswerResolver(QuestionAnswerRepository questionAnswerRepository) {
        this.questionAnswerRepository = questionAnswerRepository;
    }

    public List<QuestionAnswer> answers(ExerciseAnswer answer) {
        return questionAnswerRepository.findByExerciseAnswer(answer);
    }

}
