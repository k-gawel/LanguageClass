package language.graphql.textbook.filter;

import language.appconfig.security.UserAuthority;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.repository.textbook.TextbookAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component("textbookAccessFilter")
@RequiredArgsConstructor
public class TextbookAccessFilter {

    private final TextbookAccessRepository textbookAccessRepository;

    public boolean hasAccess(Authentication authentication, Textbook textbook) {
        var userName = authentication.getName();
        var authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        if(authorities.contains(UserAuthority.TEACHER.getAuthority()))
            return true;
        var access = textbookAccessRepository.getByTextbook(textbook.id());
        return access.isPublic() || access.allowedStudents().stream().anyMatch(u -> u.id().equals(userName));
    }

}
