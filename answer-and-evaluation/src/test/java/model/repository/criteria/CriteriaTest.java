package model.repository.criteria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CriteriaTest {

    record TestCriteria(int value1, String value2) implements Criteria{}

    @Test
    public void test() {
        var criteria = new TestCriteria(1, "2");

        assertTrue(criteria.hasValue("value1"));
        assertTrue(criteria.hasValue("value2"));
        assertFalse(criteria.hasValue("value3"));
        assertEquals(1, criteria.getValue("value1"));
        assertEquals("2", criteria.getValue("value2"));
        assertThrows(IllegalArgumentException.class, () -> criteria.getValue("value3"));
    }

}