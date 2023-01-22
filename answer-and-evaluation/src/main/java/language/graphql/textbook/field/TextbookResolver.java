package language.graphql.textbook.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.repository.textbook.ChapterRepository;
import language.contentandrepository.repository.user.TeacherRepository;
import language.graphql.shared.DataFetchingEnvironmentUtils;
import language.graphql.shared.DomainResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TextbookResolver extends DomainResolver<Textbook> implements GraphQLResolver<Textbook> {

    private final ChapterRepository chapterRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public String id(Textbook domain) {
        return super.id(domain);
    }

    public List<Chapter> chapters(Textbook textbook, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                getProxyChapters(textbook) : getFullChapters(textbook);
    }

    public Teacher author(Textbook textbook, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                getProxyTeacher(textbook) : teacherRepository.getById(textbook.author());

    }

    private List<Chapter> getFullChapters(Textbook textbook) {
        return chapterRepository.getByIds(textbook.chapters());
    }

    private List<Chapter> getProxyChapters(Textbook textbook) {
        return textbook.chapters().stream()
                .map(DomainID::id)
                .map(i -> new Chapter(new DomainID<>(Chapter.class, i), null, null))
                .toList();
    }

    private Teacher getProxyTeacher(Textbook textbook) {
        return new Teacher(textbook.author());
    }

}
