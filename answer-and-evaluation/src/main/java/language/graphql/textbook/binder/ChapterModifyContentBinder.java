package language.graphql.textbook.binder;

import language.contentandrepository.repository.textbook.ChapterContentRepository;
import language.contentandrepository.repository.textbook.ChapterRepository;
import language.graphql.shared.InputBinder;
import language.graphql.textbook.input.ModifyContentInput;
import language.service.service.textbook.inputs.ChapterContentModifyInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChapterModifyContentBinder implements InputBinder<ModifyContentInput, ChapterContentModifyInput> {

    private final ChapterRepository chapterRepository;
    private final ChapterContentRepository chapterContentRepository;

    @Override
    public ChapterContentModifyInput bind(ModifyContentInput source) {
        return new ChapterContentModifyInput(
                chapterRepository.findById(source.containerId()).orElse(null),
                chapterContentRepository.findById(source.contentId()).orElse(null),
                source.newPlace()
        );
    }

    @Override
    public Class<ModifyContentInput> consumes() {
        return ModifyContentInput.class;
    }

    @Override
    public Class<ChapterContentModifyInput> generates() {
        return ChapterContentModifyInput.class;
    }

}
