package language.graphql.textbook.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.ChapterContent;
import language.contentandrepository.model.domain.textbook.Example;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.repository.textbook.ExampleRepository;
import language.contentandrepository.repository.textbook.ExerciseRepository;
import language.graphql.shared.DataFetchingEnvironmentUtils;
import language.graphql.shared.DomainResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ChapterResolver extends DomainResolver<Chapter> implements GraphQLResolver<Chapter> {

    private final ExampleRepository exampleRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public String id(Chapter domain) {
        return super.id(domain);
    }

    public List<ChapterContent> content(Chapter chapter, DataFetchingEnvironment environment) {
        return DataFetchingEnvironmentUtils.isOnlyId(environment.getField()) ?
                getProxyContent(chapter) : getFullContent(chapter);
    }

    private List<ChapterContent> getFullContent(Chapter chapter) {
        return chapter.content().stream()
                .map(this::getFullChapterContent)
                .toList();
    }

    private List<ChapterContent> getProxyContent(Chapter chapter) {
            return chapter.content().stream()
                    .map(this::getProxyChapterContent)
                    .toList();
    }

    private ChapterContent getFullChapterContent(DomainID<ChapterContent> id) {
        if(id.type().equals(Exercise.class))
            return exerciseRepository.findById(new DomainID<>(Exercise.class, id.id())).orElseThrow();
        else if(id.type().equals(Example.class))
            return exampleRepository.findById(new DomainID<>(Example.class, id.id())).orElseThrow();
        else
            throw new IllegalArgumentException();
    }

    private ChapterContent getProxyChapterContent(DomainID<ChapterContent> id) {
        if(id.type().equals(Exercise.class))
            return new Exercise(new DomainID<>(Exercise.class, id.id()), null, null, null);
        else if(id.type().equals(Example.class))
            return new Example(new DomainID<>(Example.class, id.id()), null, null);
        else
            throw new IllegalArgumentException(id.toString());
    }

}
