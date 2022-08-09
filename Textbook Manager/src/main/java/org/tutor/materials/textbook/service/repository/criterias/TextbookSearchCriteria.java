package org.tutor.materials.textbook.service.repository.criterias;

import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.domain.type.users.Student;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;

import java.util.List;

public record TextbookSearchCriteria(
        List<ID<Textbook>> ids,
        List<ID<Teacher>> creators,
        Boolean isPublic,
        List<ID<Student>> accessibleFor
) implements Criteria<Textbook> {

}
