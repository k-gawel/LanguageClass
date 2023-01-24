package language.contentandrepository.criteria;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static language.contentandrepository.criteria.CriteriaUtils.containsAllOrEmpty;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CriteriaUtilsTest {

    @Test
    public void containsAllOrEmptyTest() {
        var collection = getIds(IntStream.range(0, 10));

        List<DomainID> elementsNull = null;
        List<DomainID> elements0 = Collections.emptyList();
        var elementsValid = getIds(IntStream.range(0,5));
        var elementsNonValid = getIds(IntStream.range(6, 11));

        assertTrue(containsAllOrEmpty(collection, elementsNull));
        assertTrue(containsAllOrEmpty(collection, elements0));
        assertTrue(containsAllOrEmpty(collection, elementsValid));
        assertFalse(containsAllOrEmpty(collection, elementsNonValid));
    }

    private static List<DomainID> getIds(IntStream idNumbers) {
        return idNumbers.mapToObj(i -> new DomainID(Domain.class, "id" + i)).toList();
    }


}