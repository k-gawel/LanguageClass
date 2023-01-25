package utils.dummyrepositories;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.repository.ContentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DummyContentRepository<E extends Domain> implements ContentRepository<E> {

    protected final List<E> list;
    private final Class<E> objectType;

    public DummyContentRepository(List<E> list, Class<E> objectType) {
        this.list = new ArrayList<>();
        this.list.addAll(list);
        this.objectType = objectType;
    }

    public DummyContentRepository(Class<E> objectType) {
        this.objectType = objectType;
        this.list = new ArrayList<>();
    }

    public DummyContentRepository(Class<E> objectType, E... object) {
        this.list = new ArrayList<>();
        this.list.addAll(Arrays.stream(object).toList());
        this.objectType = objectType;
    }

    @Override
    public Class<E> provides() {
        return objectType;
    }

    public List<E> find(Predicate<E> predicate) {
        return list.stream().filter(predicate).toList();
    }
    @Override
    public Optional<E> findById(DomainID<E> id) {
        return this.list.stream().filter(e -> e.id().equals(id)).findFirst();
    }

    @Override
    public Optional<E> findById(String id) {
        return this.list.stream().filter(e -> e.id().id().equals(id)).findFirst();
    }

    @Override
    public List<E> getByIds(List<DomainID<E>> domainIDS) {
        return this.list.stream().filter(domainIDS::contains).toList();
    }

    @Override
    public DomainID<E> generateId(String baseId) {
        var lowestIndex = list.stream()
                .map(Domain::id)
                .map(DomainID::id)
                .filter(s -> s.startsWith(baseId))
                .map(s -> s.split("__"))
                .map(s -> s[s.length - 1])
                .mapToLong(Long::parseLong)
                .min()
                .orElse(0L) + 1;

        return new DomainID<>(objectType, baseId + "__" + lowestIndex);
    }

    @Override
    public boolean add(E object) {
        return this.list.add(object);
    }

    @Override
    public void delete(E object) {
        this.list.remove(object);
    }

    @Override
    public boolean isPresent(E object) {
        return this.list.contains(object);
    }

}
