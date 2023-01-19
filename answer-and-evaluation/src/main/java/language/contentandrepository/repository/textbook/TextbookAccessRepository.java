package language.contentandrepository.repository.textbook;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.persistence.entity.textbook.TextbookAccess;
import language.contentandrepository.repository.ContentRepository;
import language.exception.NotFoundException;

import java.util.Optional;

public interface TextbookAccessRepository extends ContentRepository<TextbookAccess> {

    Optional<TextbookAccess> findByTextbook(DomainID<Textbook> textbook);

    default TextbookAccess getByTextbook(DomainID<Textbook> textbook) {
        return findByTextbook(textbook).orElseThrow(() -> new NotFoundException(textbook));
    }

    default Optional<TextbookAccess> findByTextbook(String textbookId) {
        return findByTextbook(new DomainID<>(Textbook.class, textbookId));
    }

}
