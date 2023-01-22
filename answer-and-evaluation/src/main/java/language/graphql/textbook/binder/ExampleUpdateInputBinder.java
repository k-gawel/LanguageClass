package language.graphql.textbook.binder;

import language.contentandrepository.repository.textbook.ExampleRepository;
import language.graphql.shared.InputBinder;
import language.graphql.textbook.input.ExampleInput;
import language.service.service.textbook.inputs.ExampleUpdateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExampleUpdateInputBinder implements InputBinder<ExampleInput, ExampleUpdateInput> {

    private ExampleRepository exampleRepository;

    @Override
    public ExampleUpdateInput bind(ExampleInput source) {
        return new ExampleUpdateInput(
                exampleRepository.findById(source.id()).orElse(null),
                source.title(),
                source.content()
        );
    }

    @Override
    public Class<ExampleInput> consumes() {
        return ExampleInput.class;
    }

    @Override
    public Class<ExampleUpdateInput> generates() {
        return ExampleUpdateInput.class;
    }

}
