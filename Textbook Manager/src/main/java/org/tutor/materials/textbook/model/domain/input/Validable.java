package org.tutor.materials.textbook.model.domain.input;

import java.util.List;
import java.util.Optional;

public interface Validable {

    Optional<List<String>> validate();



}
