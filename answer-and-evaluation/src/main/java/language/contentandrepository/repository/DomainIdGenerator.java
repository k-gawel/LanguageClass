package language.contentandrepository.repository;

import lombok.AllArgsConstructor;
import language.contentandrepository.model.Domain;

import java.util.stream.Stream;

@AllArgsConstructor
class DomainIdGenerator<D extends Domain> {

    private final IdsRepository cachedIdsRepository;
    private final IdsRepository persistedIdsRepository;

    public String generateNewId(String base) {
        var lowestIndex = getLowestIndex(base);
        return base + "__" + lowestIndex;
    }

    private long getLowestIndex(String base) {
        return getIds(base)
                .map(i -> i.replaceFirst((base + "__"), ""))
                .map(this::parseOrMin)
                .filter(i -> i != Long.MIN_VALUE)
                .sorted()
                .findFirst()
                .orElse(0L);
    }

    private Stream<String> getIds(String base) {
        return Stream.concat(getCachedIds(base), gerPersistedIds(base));
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
