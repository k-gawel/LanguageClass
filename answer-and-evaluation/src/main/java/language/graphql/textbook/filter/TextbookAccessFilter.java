package language.graphql.textbook.filter;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.repository.textbook.TextbookAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextbookAccessFilter {

    private final TextbookAccessRepository textbookAccessRepository;

    public boolean hasAccess(Authentication authentication, String textbookId) {
        var access = textbookAccessRepository.getById(textbookId);
        return authentication.getAuthorities().contains(UserAuthority.TEACHER) ||
                access.allowedStudents().stream()
                        .map(DomainID::id)
                        .anyMatch(i -> i.equals(authentication.getName()));
    }

}
