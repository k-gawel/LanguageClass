package org.tutor.materials.textbook.service.repository.interfaces;


import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.domain.type.users.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Optional<Student> findById(ID<Student> id);

    List<Student> findByIds(Collection<ID<Student>> ids);

    List<Student> getTextbookAccessors(ID<Textbook> textbookId);

}
