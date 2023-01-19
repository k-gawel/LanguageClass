package language.graphql.textbook.binder;

import language.graphql.shared.InputBinder;
import language.graphql.textbook.input.ChapterInput;
import language.service.service.textbook.inputs.ChapterCreateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChapterCreateInputBinder implements InputBinder<ChapterInput, ChapterCreateInput> {

    @Override
    public ChapterCreateInput bind(ChapterInput source) {
        return new ChapterCreateInput(
                source.title()
        );
    }

    @Override
    public Class<ChapterInput> consumes() {
        return ChapterInput.class;
    }

    @Override
    public Class<ChapterCreateInput> generates() {
        return ChapterCreateInput.class;
    }
}
