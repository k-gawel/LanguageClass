package org.tutor.materials.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.entity.materialsource.textbook.Textbook;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.repository.ChapterRepository;
import org.tutor.materials.repository.TextbookRepository;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    TextbookRepository textbookRepository;

    @Autowired
    ChapterRepository chapterRepository;

    public String hello() {
        return "hello motherfuckers";
    }

    public List<Textbook> getTextbooks() {
        return textbookRepository.findAll();
    };

    public Textbook getTextbook(Long id) {
        return textbookRepository.findById(id).orElse(null);
    }

    public Chapter getChapter(Long id) {
        return chapterRepository.findById(id).orElseThrow();
    }

}
