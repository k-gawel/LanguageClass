package language.graphql.textbook.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import language.contentandrepository.model.domain.textbook.Example;
import language.graphql.textbook.binder.ExampleCreateInputBinder;
import language.graphql.textbook.input.ExampleInput;
import language.service.service.textbook.services.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExampleMutation implements GraphQLMutationResolver {

    private final ExampleService exampleService;
    private final ExampleCreateInputBinder exerciseCreateInputBinder;

    @PreAuthorize("hasAuthority('TEACHER')")
    public Example createExample(ExampleInput rawInput) {
        var input = exerciseCreateInputBinder.bind(rawInput);
        return exampleService.create(input);
    }

}
