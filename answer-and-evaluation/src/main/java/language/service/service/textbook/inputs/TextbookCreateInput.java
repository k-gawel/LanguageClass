package language.service.service.textbook.inputs;

import language.contentandrepository.model.domain.user.Teacher;

public record TextbookCreateInput(
        Teacher author,
        String title
) {
}
