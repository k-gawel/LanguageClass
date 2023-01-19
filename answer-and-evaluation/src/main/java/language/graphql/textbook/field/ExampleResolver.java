package language.graphql.textbook.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import language.graphql.shared.DomainResolver;
import language.contentandrepository.model.domain.textbook.Example;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExampleResolver extends DomainResolver<Example> implements GraphQLResolver<Example> {

    @Override
    public String id(Example domain) {
        return super.id(domain);
    }

}
