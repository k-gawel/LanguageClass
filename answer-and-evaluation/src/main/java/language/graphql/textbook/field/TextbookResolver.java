package language.graphql.textbook.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import language.graphql.shared.DataFetchingEnvironmentUtils;
import language.graphql.shared.DomainResolver;
import graphql.schema.DataFetchingEnvironment;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Textbook;
import lombok.AllArgsConstructor;
import language.contentandrepository.model.DomainID;
import org.springframework.stereotype.Component;
import language.contentandrepository.repository.impl.textbook.BaseChapterRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class TextbookResolver extends DomainResolver<Textbook> implements GraphQLResolver<Textbook> {

    private final BaseChapterRepository chapterRepository;

    @Override
    public String id(Textbook domain) {
        return super.id(domain);
    }

    public List<Chapter> chapters(Textbook textbook, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                getProxyChapters(textbook) : getFullChapters(textbook);
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

}
