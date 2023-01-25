package language.contentandrepository.criteria.textbook;

import language.contentandrepository.criteria.DomainCriteria;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Example;
import lombok.Builder;

import java.util.List;

public record ExampleCriteria(
        List<DomainID<Example>> ids,
        String title
) implements DomainCriteria<Example> {

    @Builder public ExampleCriteria {}

}
