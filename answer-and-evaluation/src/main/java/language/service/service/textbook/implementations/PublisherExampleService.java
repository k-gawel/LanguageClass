package language.service.service.textbook.implementations;

import language.contentandrepository.model.domain.textbook.Example;
import language.contentandrepository.repository.impl.textbook.BaseExampleRepository;
import language.service.service.eventpublisher.DomainEvent;
import language.service.service.eventpublisher.PublishEvent;
import language.service.service.textbook.inputs.ExampleCreateInput;
import language.service.service.textbook.inputs.ExampleUpdateInput;
import language.service.service.textbook.services.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherExampleService implements ExampleService {

    private final BaseExampleRepository exampleRepository;

    @PublishEvent(DomainEvent.EXAMPLE_CREATED)
    public Example create(ExampleCreateInput input) {
        var newExample = new Example(
                exampleRepository.generateId(baseId(input)),
                input.title(),
                input.content()
        );

        exampleRepository.add(newExample);

        return newExample;
    }

    @PublishEvent(DomainEvent.EXAMPLE_MODIFIED)
    public Example updateExample(ExampleUpdateInput input) {
        var currentExample = input.example();

        var newExample = new Example(
                currentExample.id(),
                input.title() != null && !input.title().equals(currentExample.title())
                        ? input.title() : currentExample.title(),
                input.content() != null && !input.content().equals(currentExample.content())
                        ? input.content() : currentExample.content()
        );

        if(currentExample.equals(newExample)) {
            return null;
        } else {
            exampleRepository.delete(currentExample);
            exampleRepository.add(newExample);
            return newExample;
        }
    }

    private String baseId(ExampleCreateInput input) {
        return "example_" + input.title().replaceAll("\s", "_");
    }

}
