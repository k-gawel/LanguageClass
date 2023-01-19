package language.contentandrepository.repository.impl.textbook;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.ChapterContent;
import language.contentandrepository.model.domain.textbook.Example;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.repository.ContentRepository;
import language.contentandrepository.repository.textbook.ChapterContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Repository("chapterContentRepository")
@RequiredArgsConstructor
public class BaseChapterContentRepository implements ChapterContentRepository {

    private final BaseExerciseRepository exerciseRepository;
    private final BaseExampleRepository exampleRepository;

    @Override
    public Optional<ChapterContent> findById(DomainID id) {
        return getRepository(id).findById(id);
    }

    @Override
    public ChapterContent getById(DomainID id) {
        return (ChapterContent) getRepository(id).getById(id);
    }

    @Override
    public List<ChapterContent> find(Predicate<ChapterContent> predicate) {
        var result = new ArrayList<ChapterContent>();
        result.addAll(exerciseRepository.find(predicate::test));
        result.addAll(exampleRepository.find(predicate::test));
        return result;
    }

    @Override
    public Optional<ChapterContent> findById(String id) {
        var exercise = exerciseRepository.findById(id);
        if(exercise.isPresent())
            return Optional.of(exercise.get());
        var example = exampleRepository.findById(id);
        if(example.isPresent())
            return Optional.of(example.get());
        return Optional.empty();
    }

    public ChapterContent getById(String id) {
        return exerciseRepository
                .findById(id).map(o -> (ChapterContent) o)
                .orElseGet(() -> exampleRepository.getById(id));
    }

    @Override
    public List<ChapterContent> getByIds(List<DomainID<ChapterContent>> domainIDS) {
        return domainIDS.stream()
                .map(i -> getRepository(i).getById(i))
                .map(i -> (ChapterContent) i)
                .toList();
    }

    @Override
    public DomainID<ChapterContent> generateId(String baseId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(ChapterContent object) {
        return getRepository(object).add(object);
    }

    @Override
    public void delete(ChapterContent object) {
        getRepository(object).delete(object);
    }

    @Override
    public boolean isPresent(ChapterContent object) {
        return getRepository(object).isPresent(object);
    }

    private ContentRepository getRepository(ChapterContent object) {
        if(object instanceof Exercise)
            return  exerciseRepository;
        else if(object instanceof Example)
            return exampleRepository;
        else
            throw new IllegalArgumentException(String.valueOf(Objects.requireNonNull(object).getClass()));
    }

    private ContentRepository getRepository(DomainID id) {
        Objects.requireNonNull(id);
        if(id.type().equals(Exercise.class))
            return exerciseRepository;
        else if(id.type().equals(Example.class))
            return exampleRepository;
        else
            throw new IllegalArgumentException(id.type().toString());
    }

}
