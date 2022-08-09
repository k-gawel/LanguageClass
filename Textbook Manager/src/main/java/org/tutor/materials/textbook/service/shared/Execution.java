package org.tutor.materials.textbook.service.shared;

import org.apache.commons.lang3.function.TriFunction;

import java.util.ArrayList;
import java.util.List;

interface Execution extends TriFunction<List<String>, String, Integer, List<String>> {

    // places child in content list on desired place. If child already exists in list, removes previous occurrence.
    static List<String> place(List<String> content, String child, Integer place) {
        var result = new ArrayList<>(content);
        result.remove(child);
        if(place == null || place > content.size())
            result.add(child);
        else if(place <= 1)
            result.add(0, child);
        else
            result.add(place - 1, child);
        return result;
    }

    static List<String> remove(List<String> content, String child, Integer place) {
        var result = new ArrayList<>(content);
        result.remove(child);
        return result;
    }
}
