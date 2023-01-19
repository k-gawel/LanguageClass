package language.service.service.textbook.inputs;

import language.contentandrepository.model.domain.textbook.Example;

public record ExampleUpdateInput(
        Example example,
        String title,
        String content
) {
}
