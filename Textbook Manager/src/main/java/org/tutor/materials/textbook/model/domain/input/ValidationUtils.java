package org.tutor.materials.textbook.model.domain.input;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

public final class ValidationUtils {

    //return false if collection is empty or all of its elements are blank strings
    public static boolean isNonBlank(List<String> collection) {
        return CollectionUtils.isEmpty(collection) &&
                collection.stream().anyMatch(s -> !Objects.isNull(s) && !s.isBlank());
    }

    public static boolean isBlank(List<String> collection) {
        return !isNonBlank(collection);
    }

    //todo write test
    //returns true if any collection is empty or any nested collection is empty or any string in it is blank
    //otherwise returns false
    public static boolean areBlank(List<List<String>> list) {
        return CollectionUtils.isEmpty(list) ||
                list.stream().anyMatch(ValidationUtils::isBlank);
    }


    public static boolean areNonBlank(List<List<String>> list) {
        return !areBlank(list);
    }



}
