package language.graphql.textbook.filter;

import language.contentandrepository.criteria.textbook.TextbookCriteria;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.model.domain.user.AppUser;
import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.repository.textbook.TextbookAccessRepository;
import language.contentandrepository.repository.textbook.TextbookElementsRepository;
import language.contentandrepository.repository.user.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("textbookAccessFilter")
@RequiredArgsConstructor
public class TextbookElementsFilter {

    private final AppUserRepository appUserRepository;
    private final TextbookAccessRepository textbookAccessRepository;
    private final TextbookElementsRepository textbookElementsRepository;

    public boolean hasAccess(Authentication authentication, Textbook textbook) {
        return hasAccess(appUserRepository.getById(authentication.getName()), textbook);
    }

    public boolean hasAccess(AppUser user, Textbook textbook) {
        if(user == null) return false;
        if(user instanceof Teacher)
            return true;
        var access = textbookAccessRepository.getByTextbook(textbook.id());
        return access.isPublic() || access.allowedStudents().stream().anyMatch(u -> u.equals(user.id()));
    }

    public boolean hasAccess(Authentication authentication, Chapter chapter) {
        return hasAccess(appUserRepository.getById(authentication.getName()), chapter);
    }

    public boolean hasAccess(AppUser user, Chapter chapter) {
        if(user == null)
            return false;
        if(user instanceof Teacher)
            return true;

        return textbookElementsRepository.find(
                TextbookCriteria.builder().containedChapters(List.of(chapter.id().id())).build()
        ).stream().anyMatch(t -> hasAccess(user, t));
    }

}
