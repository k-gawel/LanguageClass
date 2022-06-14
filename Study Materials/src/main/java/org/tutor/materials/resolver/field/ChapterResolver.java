package org.tutor.materials.resolver.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.ChapterPart;
import org.tutor.materials.repository.ChapterRepository;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ChapterResolver implements GraphQLResolver<Chapter> {

    private final ChapterRepository repository;

    @Autowired
    public ChapterResolver(ChapterRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<ChapterPart> content(Chapter chapter) {
        return repository.findByChapter(chapter);
    }

}
