package language.contentandrepository.criteria;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

public class CriteriaUtils {



    public static boolean containsOrEmpty(Collection collection, Object domainID) {
        return isEmpty(collection) || collection.contains(domainID);
    }

    public static boolean containsOrEmpty(String string, String subString) {
        final String regex = "[^aA-zZ0-9]";
        return StringUtils.isBlank(subString) ||
                string.replaceAll(regex, "").contains(subString.replaceAll(regex, ""));
    }

    public static boolean containsAllOrEmpty(Collection<? extends DomainID> collection, Collection<? extends DomainID> elements) {
        if(isEmpty(elements))
            return true;
        return collection.containsAll(elements);
    }

    public static boolean containsAllOrEmpty(Supplier<? extends Collection<? extends DomainID<? extends Domain>>> collectionSupplier, Collection<? extends DomainID<? extends Domain>> elements) {
        return isEmpty(elements) || containsAllOrEmpty(collectionSupplier.get(), elements);
    }

    public static boolean allContainsOrEmpty(Collection<? extends Collection<? extends DomainID>> collections, String id) {
        return id == null || collections.stream().allMatch(c -> c.stream().map(DomainID::id).anyMatch(i -> i.equals(id)));
    }

    public static boolean allContainsOrEmpty(Supplier<? extends Collection<? extends Collection<? extends DomainID>>> collectionsSupplier, String id) {
        return id == null || allContainsOrEmpty(collectionsSupplier.get(), id);
    }

    public static boolean isBetween(LocalDateTime startDate, LocalDateTime endDate, LocalDateTime date) {
        return ( Objects.isNull(startDate) || startDate.isBefore(date) || startDate.equals(date) ) &&
                (Objects.isNull(endDate) || endDate.isAfter(date) || endDate.equals(date) );
    }

    public static boolean equals(Object o1, Object o2) {
        return o1 == null || o1.equals(o2);
    }

    private static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

}
