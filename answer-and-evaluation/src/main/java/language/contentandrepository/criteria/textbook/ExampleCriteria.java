package language.contentandrepository.criteria.textbook;

import language.contentandrepository.criteria.DomainType;
import language.contentandrepository.model.domain.textbook.Example;
import lombok.Builder;

import java.util.List;

public record ExampleCriteria(
        @DomainType(Example.class)
        List<String> ids,
        String title
) {

    @Builder public ExampleCriteria {}

}
