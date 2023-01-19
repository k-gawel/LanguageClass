package language.contentandrepository.criteria.textbook;

import lombok.Builder;

import java.util.List;

public record ExampleCriteria(
        List<String> ids,
        String title
) {

    @Builder public ExampleCriteria {}

}
