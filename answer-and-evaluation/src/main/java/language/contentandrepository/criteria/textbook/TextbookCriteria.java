package language.contentandrepository.criteria.textbook;

import language.contentandrepository.criteria.DomainType;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record TextbookCriteria(
        @DomainType(Textbook.class)
        List<String> ids,
        String title,
        @DomainType(Teacher.class)
        List<String> authors,
        @DomainType(Student.class)
        List<String> allowedUsers,
        @DomainType(Chapter.class)
        List<String> containedChapters,
        LocalDateTime after,
        LocalDateTime before
) {

    @Builder(access = AccessLevel.PUBLIC)
    public TextbookCriteria {}

}
