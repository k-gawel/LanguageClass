package language.graphql.textbook.binder;

import language.contentandrepository.repository.textbook.ChapterRepository;
import language.contentandrepository.repository.textbook.TextbookRepository;
import language.graphql.shared.InputBinder;
import language.graphql.textbook.input.ModifyContentInput;
import language.service.service.textbook.inputs.TextbookContentModifyInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextbookModifyContentInputBinder implements InputBinder<ModifyContentInput, TextbookContentModifyInput> {

    private final TextbookRepository textbookRepository;
    private final ChapterRepository chapterRepository;

    @Override
    public TextbookContentModifyInput bind(ModifyContentInput source) {
        return new TextbookContentModifyInput(
                textbookRepository.findById(source.containerId()).orElse(null),
                chapterRepository.findById(source.containerId()).orElse(null),
                source.newPlace()
        );
    }

    @Override
    public Class<ModifyContentInput> consumes() {
        return ModifyContentInput.class;
    }

    @Override
    public Class<TextbookContentModifyInput> generates() {
        return TextbookContentModifyInput.class;
    }

}
