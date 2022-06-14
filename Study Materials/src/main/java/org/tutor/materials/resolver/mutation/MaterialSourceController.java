package org.tutor.materials.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.dto.input.ChapterInput;
import org.tutor.materials.model.dto.input.TextbookInput;
import org.tutor.materials.model.entity.materialsource.textbook.Textbook;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.repository.TextbookRepository;
import org.tutor.materials.service.materialsource.TextbookService;
import org.tutor.materials.service.materialsource.chapter.ChapterService;

@Component
public class MaterialSourceController implements GraphQLMutationResolver {

    private final TextbookRepository textbookRepository;
    private final TextbookService textbookService;
    private final ChapterService chapterService;

    @Autowired
    public MaterialSourceController(TextbookRepository textbookRepository, TextbookService textbookService, ChapterService chapterService) {
        this.textbookRepository = textbookRepository;
        this.textbookService = textbookService;
        this.chapterService = chapterService;
    }

    public Textbook addTextbook(TextbookInput input) {
        return textbookService.createNewTextbook(null, input);
    }

    public Chapter addChapter(Long textbookId, ChapterInput input) {
        var textbook = textbookRepository.findByIdOrThrow(textbookId);

        return chapterService.createChapter(textbook, input);
    }

}
