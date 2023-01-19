package language.graphql.textbook.binder;

import language.contentandrepository.repository.textbook.ChapterRepository;
import language.graphql.shared.InputBinder;
import language.graphql.textbook.input.ChapterInput;
import language.graphql.textbook.input.ChapterUpdateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChapterUpdateInputBinder implements InputBinder<ChapterInput, ChapterUpdateInput> {

    private final ChapterRepository chapterRepository;

    @Override
    public ChapterUpdateInput bind(ChapterInput source) {
        return new ChapterUpdateInput(
                chapterRepository.findById(source.chapter()).orElse(null),
                source.title()
        );
    }

    @Override
    public Class<ChapterInput> consumes() {
        return ChapterInput.class;
    }

    @Override
    public Class<ChapterUpdateInput> generates() {
        return ChapterUpdateInput.class;
    }
}
