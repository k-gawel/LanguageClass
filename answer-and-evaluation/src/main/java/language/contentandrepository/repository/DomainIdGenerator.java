package language.contentandrepository.repository;

import language.contentandrepository.model.Domain;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@AllArgsConstructor
class DomainIdGenerator<D extends Domain> {

    private final IdsRepository cachedIdsRepository;
    private final IdsRepository persistedIdsRepository;
    private final Set<String> nonPersistedIds = new HashSet<>();

    public String generateNewId(String base) {
        var lowestIndex = getHighestIndex(base) + 1;
        var id = base + "__" + lowestIndex;
        nonPersistedIds.add(id);
        return id;
    }

    private long getHighestIndex(String base) {
        return getIds(base)
                .map(i -> i.replaceFirst((base + "__"), ""))
                .mapToLong(this::parseOrMin)
                .filter(i -> i != Long.MIN_VALUE)
                .max()
                .orElse(0L);
    }

    private Stream<String> getIds(String base) {
        return Stream.concat(Stream.concat(getCachedIds(base), gerPersistedIds(base)), nonPersistedIds.stream());
    }

    private Stream<String> getCachedIds(String base) {
        return cachedIdsRepository.findIdsStartingWith(base).stream();
    }

    private Stream<String> gerPersistedIds(String base) {
        return persistedIdsRepository.findIdsStartingWith(base).stream();
    }

    private long parseOrMin(String string) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            return Long.MIN_VALUE;
        }
    }

}
