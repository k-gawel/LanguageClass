package org.tutor.materials.resolver.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.entity.materialsource.textbook.Textbook;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.repository.ChapterRepository;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class TextbookResolver implements GraphQLResolver<Textbook> {

    @Autowired
    ChapterRepository chapterRepository;

    @Transactional
    public List<Chapter> chapters(Textbook textbook) {
        return chapterRepository.findByTextbook(textbook);
    }

}
