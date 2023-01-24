package language.contentandrepository.criteria.textbook;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Example;
import lombok.Builder;

import java.util.List;

public record ExampleCriteria(
        List<DomainID<Example>> ids,
        String title
) {

    @Builder public ExampleCriteria {}

}
