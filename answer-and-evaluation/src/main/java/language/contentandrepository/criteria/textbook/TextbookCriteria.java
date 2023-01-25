package language.contentandrepository.criteria.textbook;

import language.contentandrepository.criteria.DomainCriteria;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record TextbookCriteria(
        List<DomainID<Textbook>> ids,
        String title,
        List<DomainID<Teacher>> authors,
        List<DomainID<Student>> allowedUsers,
        List<DomainID<Chapter>> containedChapters,
        LocalDateTime after,
        LocalDateTime before
) implements DomainCriteria<Textbook> {

    @Builder(access = AccessLevel.PUBLIC)
    public TextbookCriteria {}

}
