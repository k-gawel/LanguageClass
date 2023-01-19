package language.graphql.textbook.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import language.contentandrepository.criteria.textbook.TextbookCriteria;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.repository.textbook.TextbookElementsRepository;
import language.contentandrepository.repository.textbook.TextbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TextbookQuery implements GraphQLQueryResolver {

    private final TextbookElementsRepository textbookElementsRepository;
    private final TextbookRepository textbookRepository;

    @PreAuthorize("hasAnyAuthority('TEACHER', 'STUDENT')")
    @PostAuthorize("@textbookAccessFilter.hasAccess(authentication, returnObject)")
    public Textbook getTextbook(String id) {
        return textbookRepository.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('TEACHER', 'STUDENT')")
    @PostFilter("@textbookAccessFilter.hasAccess(authentication, filterObject)")
    public List<Textbook> findTextbook(TextbookCriteria criteria) {
        return textbookElementsRepository.find(criteria);
    }

}
