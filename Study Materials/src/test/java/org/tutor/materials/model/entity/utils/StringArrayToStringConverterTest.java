package org.tutor.materials.model.entity.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class StringArrayToStringConverterTest {

    StringArrayToStringConverter converter = new StringArrayToStringConverter();
    Function<List<String>, String> toString = converter::convertToDatabaseColumn;
    Function<String, List<String>> fromString = converter::convertToEntityAttribute;

    @Test
    public void when_NullList_expected_Null() {
        List<String> nullList = null;
        var actualResult = toString.apply(nullList);
        assertNull(actualResult);
    }

    @Test
    public void when_EmptyList_expected_Null() {
        List<String> emptyList = Collections.emptyList();
        var actualResult = toString.apply(emptyList);
        assertNull(actualResult);
    }

    @Test
    public void when_ProperList_expected_Serialized() {
        List<String> properList = List.of("A", "B", "C");
        var expected = "[\"A\",\"B\",\"C\"]";
        var actual = toString.apply(properList);
        assertEquals(expected, actual);
    }

    @Test
    public void when_HaveSpecialCharacters_expected_Serialized() {
        List<String> listWithSpecialCharacters = List.of("\"A\"", "\"B\"");
        var expected = "[\"\\\"A\\\"\",\"\\\"B\\\"\"]";
        var actual = toString.apply(listWithSpecialCharacters);
        assertEquals(expected, actual);
    }

    @Test
    public void when_NullString_expected_EmptyArrayList() {
        String nullString = null;
        var actual = fromString.apply(nullString);
        assertInstanceOf(ArrayList.class, actual);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void when_BlankString_expectedEmptyArrayList() {
        String blankString = "";
        var actual = fromString.apply(blankString);
        assertInstanceOf(ArrayList.class, actual);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void when_EmptyBraces_expectedEmptyArrayList() {
        String blankString = "[]";
        var actual = fromString.apply(blankString);
        assertInstanceOf(ArrayList.class, actual);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void when_ProperString_expectedDeserializedArray() {
        String blankString = "[\"A\",\"B\"]";
        var actual = fromString.apply(blankString);
        assertInstanceOf(ArrayList.class, actual);
        assertEquals("A", actual.get(0));
        assertEquals("B", actual.get(1));
    }

}