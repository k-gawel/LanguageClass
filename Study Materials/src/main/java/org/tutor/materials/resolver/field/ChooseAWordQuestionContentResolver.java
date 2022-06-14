package org.tutor.materials.resolver.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.ChooseAWordContent;
import org.tutor.materials.repository.question.ChooseAWordQuestionRepository;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class ChooseAWordQuestionContentResolver implements GraphQLResolver<ChooseAWordContent> {

    private final ChooseAWordQuestionRepository repository;

    @Autowired
    public ChooseAWordQuestionContentResolver(ChooseAWordQuestionRepository repository) {
        this.repository = repository;
    }

    public List<String> content(ChooseAWordContent question) {
        return question.getContentParts();
    }


}
