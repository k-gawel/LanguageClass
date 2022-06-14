package org.tutor.materials.resolver.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContent;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.content.QuestionContent;
import org.tutor.materials.repository.ExerciseContentRepository;

import java.util.List;

@Component
public class ExerciseContentResolver implements GraphQLResolver<ExerciseContent> {

    private final ExerciseContentRepository repository;

    @Autowired
    public ExerciseContentResolver(ExerciseContentRepository repository) {
        this.repository = repository;
    }

    public List<QuestionContent> questions(ExerciseContent exerciseContent) {
        return repository.getQuestions(exerciseContent);
    }



}
