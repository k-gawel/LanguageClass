package language.graphql.textbook.binder;

import language.graphql.shared.InputBinder;
import language.graphql.textbook.input.ExampleInput;
import language.service.service.textbook.inputs.ExampleCreateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExampleCreateInputBinder implements InputBinder<ExampleInput, ExampleCreateInput> {

    @Override
    public ExampleCreateInput bind(ExampleInput source) {
        return new ExampleCreateInput(
                source.title(),
                source.content()
        );
    }

    @Override
    public Class<ExampleInput> consumes() {
        return ExampleInput.class;
    }

    @Override
    public Class<ExampleCreateInput> generates() {
        return ExampleCreateInput.class;
    }
}
