package language.graphql.textbook.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.repository.textbook.TextbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextbookQuery implements GraphQLQueryResolver {

    private final TextbookRepository textbookRepository;

    @PreAuthorize("hasAnyAuthority('TEACHER', 'STUDENT')")
    public Textbook getTextbook(String id) {
        var textbook = textbookRepository.getById(id);
        return textbook;
    }

}
